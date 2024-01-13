package com.example.battlenavalserver.model;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.List;

import org.thymeleaf.standard.expression.Each;

public class Game {
    private final String gameId;
    private final String teamName;
    private final List<Ship> ships;
    private final String[][] grid;
    private int shotsFired;

    public Game(String gameId, String teamName) {
        this.gameId = gameId;
        this.teamName = teamName;
        this.ships = new ArrayList<>();
        this.grid = new String[10][10];
        initializeGrid();
        placeShips(); // You need to implement this method
        this.shotsFired = 0;
    }

    private void initializeGrid() {
        // Initialize the grid with empty cells
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = " ";
            }
        }
    }

    private void placeShips() {
        // You need to implement the logic for placing ships on the grid
        // based on the game rules (size, orientation, no overlapping, etc.)
        // A = Aircraft carrier: 1* 4 conscutive squares
        // C = Cruisers: 2* Each occupies 3 squares
        // D = Destroyers: 3* Each occupies 2 squares
        // T = Torpedo Boats: 4* Each occupies 1 square
        int randomRow;
        int randomColumn;
        /*for (int i=0; i<10; i++){
            randomRow[i] = 0;
        }
        for (i=0; i<10; i++){
            randomColumn[i] = 0;
        }/* */

        //Placement strategy :
        // - First determine randomly the square where the head of the boat is
        // - Then determine randomly the way the boat is going:
        // meaning 1 up, 2 down, 3 left, 4 right

        //Aicraft making
        Ship A = new Ship("A", 4);
        ships.add(A);
        //Aircraft's head
        randomColumn = ThreadLocalRandom.current().nextInt(4, 8);
        randomRow = ThreadLocalRandom.current().nextInt(4, 8);
        char column = (char)(randomColumn+64);
        A.getCoordinates(A).add("" + column + randomRow);
        grid[randomColumn][randomRow] = "A";
        
        //Way Aircraft's going
        int direction = ThreadLocalRandom.current().nextInt(1, 5);
        switch (direction) {
            case 1:
                A.getCoordinates(A).add("" + column + randomRow+1); 
                grid[randomColumn][randomRow+1] = "A";

                A.getCoordinates(A).add("" + column + randomRow+2); 
                grid[randomColumn][randomRow+2] = "A";

                A.getCoordinates(A).add("" + column + randomRow+3); 
                grid[randomColumn][randomRow+3] = "A";
                break;
            case 2:
                A.getCoordinates(A).add("" + column + (randomRow-1)); 
                grid[randomColumn][randomRow-1] = "A";

                A.getCoordinates(A).add("" + column + (randomRow-2)); 
                grid[randomColumn][randomRow-2] = "A";

                A.getCoordinates(A).add("" + column + (randomRow-3)); 
                grid[randomColumn][randomRow-3] = "A";
                break;
            case 3:
                column = (char)(randomColumn+63);
                A.getCoordinates(A).add("" + column + randomRow); 
                grid[randomColumn-1][randomRow] = "A";

                column = (char)(randomColumn+62);
                A.getCoordinates(A).add("" + column + randomRow); 
                grid[randomColumn-2][randomRow] = "A";

                column = (char)(randomColumn+61);
                A.getCoordinates(A).add("" + column + randomRow); 
                grid[randomColumn-3][randomRow] = "A";
                break;
            case 4:
                column = (char)(randomColumn+65);
                A.getCoordinates(A).add("" + column + randomRow); 
                grid[randomColumn+1][randomRow] = "A";

                column = (char)(randomColumn+66);
                A.getCoordinates(A).add("" + column + randomRow);  
                grid[randomColumn+2][randomRow] = "A";

                column = (char)(randomColumn+67);
                A.getCoordinates(A).add("" + column + randomRow); 
                grid[randomColumn+3][randomRow] = "A";
                break;    
            default:
                break;
        }
        //Cruisers making

        //Cruiser's head
        int empty = 0;
        Ship C1 = new Ship("C1", 3);
        while(empty == 0){
            randomColumn = ThreadLocalRandom.current().nextInt(3, 9);
            randomRow = ThreadLocalRandom.current().nextInt(3, 9);
            if(grid[randomColumn][randomRow] == "A"){
                column  = (char)(randomColumn+64);
                C1.getCoordinates(C1).add("" + column + randomRow);
                grid[randomColumn][randomRow] = "C1";
                ships.add(C1);
                empty = 1;
            }
        }

        //Way Cruiser's going
        //For the first cruiser, only 1 direction can be occupied by the aircraft
        if(grid[randomColumn][randomRow+1] != "A" || grid[randomColumn][randomRow+2] != "A"){
            //1 means down, 2 left, 3 right
            direction = ThreadLocalRandom.current().nextInt(1, 4);
            switch (direction) {
                case 1:
                    C1.getCoordinates(C1).add("" + column + (randomRow-1));
                    grid[randomColumn][randomRow-1] = "C1";

                    C1.getCoordinates(C1).add("" + column + (randomRow-2));
                    grid[randomColumn][randomRow-2] = "C1";
                    break;
                case 2:
                    column = (char)(randomColumn+63);
                    C1.getCoordinates(C1).add("" + column + randomRow);
                    grid[randomColumn-1][randomRow] = "C1";

                    column = (char)(randomColumn+62);
                    C1.getCoordinates(C1).add("" + column + randomRow);
                    grid[randomColumn-2][randomRow] = "C1";
                    break;
                case 3:
                    column = (char)(randomColumn+65);
                    C1.getCoordinates(C1).add("" + column + randomRow);
                    grid[randomColumn+1][randomRow] = "C1";

                    column = (char)(randomColumn+66);
                    C1.getCoordinates(C1).add("" + column + randomRow);
                    grid[randomColumn+2][randomRow] = "C1";
                    break;
                default:
                    break;
            }
        }
        else if (grid[randomColumn][randomRow-1] != "A" || grid[randomColumn][randomRow-2] != "A"){
            //1 means up, 2 left, 3 right
            direction = ThreadLocalRandom.current().nextInt(1, 4);
            switch (direction) {
                case 1:
                    C1.getCoordinates(C1).add("" + column + randomRow+1); 
                    grid[randomColumn][randomRow+1] = "C1";

                    C1.getCoordinates(C1).add("" + column + randomRow+2); 
                    grid[randomColumn][randomRow+2] = "C1";
                    break;
                case 2:
                    column = (char)(randomColumn+63);
                    C1.getCoordinates(C1).add("" + column + randomRow);
                    grid[randomColumn-1][randomRow] = "C1";

                    column = (char)(randomColumn+62);
                    C1.getCoordinates(C1).add("" + column + randomRow);
                    grid[randomColumn-2][randomRow] = "C1";
                    break;
                case 3:
                    column = (char)(randomColumn+65);
                    C1.getCoordinates(C1).add("" + column + randomRow);
                    grid[randomColumn+1][randomRow] = "C1";

                    column = (char)(randomColumn+66);
                    C1.getCoordinates(C1).add("" + column + randomRow);
                    grid[randomColumn+2][randomRow] = "C1";
                    break;
                default:
                    break;
            }
        }
        else if(grid[randomColumn-1][randomRow] != "A" || grid[randomColumn-2][randomRow] != "A"){
            //1 means up, 2 down, 3 right
            direction = ThreadLocalRandom.current().nextInt(1, 4);
            switch (direction) {
                case 1:
                    C1.getCoordinates(C1).add("" + column + randomRow+1); 
                    grid[randomColumn][randomRow+1] = "C1";

                    C1.getCoordinates(C1).add("" + column + randomRow+2); 
                    grid[randomColumn][randomRow+2] = "C1";
                    break;
                case 2:
                    C1.getCoordinates(C1).add("" + column + (randomRow-1));
                    grid[randomColumn][randomRow-1] = "C1";

                    C1.getCoordinates(C1).add("" + column + (randomRow-2)); 
                    grid[randomColumn][randomRow-2] = "C1";
                    break;
                case 3:
                    column = (char)(randomColumn+65);
                    C1.getCoordinates(C1).add("" + column + randomRow);
                    grid[randomColumn+1][randomRow] = "C1";

                    column = (char)(randomColumn+66);
                    C1.getCoordinates(C1).add("" + column + randomRow);
                    grid[randomColumn+2][randomRow] = "C1";
                    break;
                default:
                    break;
            }
        }
        else if(grid[randomColumn+1][randomRow] != "A" || grid[randomColumn+2][randomRow] != "A"){
            //1 means up, 2 down, 3 left
            direction = ThreadLocalRandom.current().nextInt(1, 4);
            switch (direction) {
                case 1:
                    C1.getCoordinates(C1).add("" + column + randomRow+1); 
                    grid[randomColumn][randomRow+1] = "C1";

                    C1.getCoordinates(C1).add("" + column + randomRow+2); 
                    grid[randomColumn][randomRow+2] = "C1";
                    break;
                case 2:
                    C1.getCoordinates(C1).add("" + column + (randomRow-1));
                    grid[randomColumn][randomRow-1] = "C1";

                    C1.getCoordinates(C1).add("" + column + (randomRow-2));
                    grid[randomColumn][randomRow-2] = "C1";
                    break;
                case 3:
                    column = (char)(randomColumn+63);
                    C1.getCoordinates(C1).add("" + column + randomRow);
                    grid[randomColumn-1][randomRow] = "C1";

                    column = (char)(randomColumn+62);
                    C1.getCoordinates(C1).add("" + column + randomRow);
                    grid[randomColumn-2][randomRow] = "C1";
                    break;
                default:
                    break;
            }
        }
        else{
            direction = ThreadLocalRandom.current().nextInt(1, 5);
            switch (direction) {
                case 1:
                    C1.getCoordinates(C1).add("" + column + randomRow+1); 
                    grid[randomColumn][randomRow+1] = "C1";

                    C1.getCoordinates(C1).add("" + column + randomRow+2); 
                    grid[randomColumn][randomRow+2] = "C1";
                    break;
                case 2:
                    C1.getCoordinates(C1).add("" + column + (randomRow-1));
                    grid[randomColumn][randomRow-1] = "C1";

                    C1.getCoordinates(C1).add("" + column + (randomRow-2));
                    grid[randomColumn][randomRow-2] = "C1";
                    break;
                case 3:
                    column = (char)(randomColumn+63);
                    C1.getCoordinates(C1).add("" + column + randomRow);
                    grid[randomColumn-1][randomRow] = "C1";

                    column = (char)(randomColumn+62);
                    C1.getCoordinates(C1).add("" + column + randomRow);
                    grid[randomColumn-2][randomRow] = "C1";
                    break;
                case 4:
                    column = (char)(randomColumn+65);
                    C1.getCoordinates(C1).add("" + column + randomRow);
                    grid[randomColumn+1][randomRow] = "C1";

                    column = (char)(randomColumn+66);
                    C1.getCoordinates(C1).add("" + column + randomRow);
                    grid[randomColumn+2][randomRow] = "C1";
                    break;    
                default:
                    break;
            }
        }


        Ship C2 = new Ship("C2", 3);
        ships.add(C2);
        
        //Destroyers making
        Ship D1 = new Ship("D1", 2);
        Ship D2 = new Ship("D2", 2);
        Ship D3 = new Ship("D3", 2);
        ships.add(D1);
        ships.add(D2);
        ships.add(D2);

        //Torpedos making
        empty = 0;
        Ship T1 = new Ship("T1", 1);
        while(empty == 0){
            randomColumn = ThreadLocalRandom.current().nextInt(1, 11);
            randomRow = ThreadLocalRandom.current().nextInt(1, 11);
            if(grid[randomColumn][randomRow] == "A"){
                char columnT = (char)(randomColumn+64);
                T1.getCoordinates(T1).add("" + column + randomRow); 
                grid[randomColumn][randomRow] = "T1";
                ships.add(T1);
                empty = 1;
            }
        }

        empty = 0;
        Ship T2 = new Ship("T2", 1);
        while(empty == 0){
            randomColumn = ThreadLocalRandom.current().nextInt(1, 11);
            randomRow = ThreadLocalRandom.current().nextInt(1, 11);
            if(grid[randomColumn][randomRow] == "A"){
                char columnT = (char)(randomColumn+64);
                T2.getCoordinates(T2).add("" + column + randomRow); 
                grid[randomColumn][randomRow] = "T2";
                ships.add(T2);
                empty = 1;
            }
        }

        empty = 0;
        Ship T3 = new Ship("T3", 1);
        while(empty == 0){
            randomColumn = ThreadLocalRandom.current().nextInt(1, 11);
            randomRow = ThreadLocalRandom.current().nextInt(1, 11);
            if(grid[randomColumn][randomRow] == "A"){
                char columnT = (char)(randomColumn+64);
                T3.getCoordinates(T3).add("" + column + randomRow); 
                grid[randomColumn][randomRow] = "T3";
                ships.add(T3);
                empty = 1;
            }
        }

        empty = 0;
        Ship T4 = new Ship("T4", 1);
        while(empty == 0){
            randomColumn = ThreadLocalRandom.current().nextInt(1, 11);
            randomRow = ThreadLocalRandom.current().nextInt(1, 11);
            if(grid[randomColumn][randomRow] == "A"){
                char columnT = (char)(randomColumn+64);
                T4.getCoordinates(T4).add("" + column + randomRow); 
                grid[randomColumn][randomRow] = "T4";
                ships.add(T4);
                empty = 1;
            }
        }

        //Conversion to to char from int : (char)(a+64) (1 becomes A, 2 becomes B, ...)
    }

    public String getGameId() {
        return gameId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String[][] getGrid() {
        return grid;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public int getShotsFired() {
        return shotsFired;
    }

    public void incrementShotsFired() {
        shotsFired++;
    }

    public String processFiring(int row, int column) {
        if (row < 1 || row > 10 || column < 1 || column > 10) {
            return "Coordinates not on the board";  
        }
    
        String cellStatus = grid[row - 1][column - 1];
        switch (cellStatus) {
            case "A":  // Empty cell - miss
                grid[row - 1][column - 1] = "M";
                incrementShotsFired();
                return "miss";
            case "S":  // Ship cell - hit
                grid[row - 1][column - 1] = "H";
                incrementShotsFired();
    
                /*for (Ship ship : ships) {
                    if (ship.isHit(row, column)) {
                        ship.markCoordinateAsHit(row, column);
                        if (ship.isSunk()) {
                            return "sunk";
                        }
                        return "hit";
                    }
                }*/
                return "Unexpected error in processing hit"; 
            case "M":  // Already missed - treat as a miss
                return "miss";
            case "H":  // Already hit - treat as a hit
                return "hit";
            default:
                return "Unexpected cell status";
        }
    }
    
    private static class Coordinate {
        private final int row;
        private final int column;
        private boolean isHit;
    
        public Coordinate(int row, int column) {
            this.row = row;
            this.column = column;
            this.isHit = false;
        }
    
        public void markAsHit() {
            this.isHit = true;
        }
    }

    // You may need additional methods for game logic, such as processing a fire command,
    // checking for a win, handling errors, etc.
}
