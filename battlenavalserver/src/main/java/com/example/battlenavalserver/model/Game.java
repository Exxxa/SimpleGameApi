package com.example.battlenavalserver.model;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.List;

//import org.thymeleaf.standard.expression.Each;

public class Game {
    private final String gameId;
    private final String teamName;
    private List<Ship> ships;
    public String[][] grid;
    private int shotsFired;

    public Game(String gameId, String teamName, List<Ship> ships2, String[][] grid2, int i) {
        this.gameId = gameId;
        this.teamName = teamName;
        this.ships = new ArrayList<>();
        grid = new String[10][10];
        initializeGrid(grid);
        placeShips(); // You need to implement this method
        this.shotsFired = 0;
    }

    private void initializeGrid(String[][] grid) {
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
        
        //Conversion to to char from int : a = 1; (char)(a+64) (1 becomes A, 2 becomes B, ...)

        //Placement strategy :
        // - First determine randomly the square where the head of the boat is
        // - Then determine randomly the way the boat is going:
        // meaning 1 up, 2 down, 3 left, 4 right
        int randomRow;
        int randomColumn;

        //Aicraft making
        Ship A = new Ship("A", 4);
        //Aircraft's head
        randomColumn = ThreadLocalRandom.current().nextInt(4, 8);
        randomRow = ThreadLocalRandom.current().nextInt(4, 8);
        char column = (char)(randomColumn+64);
        A.setCoordinates("" + column + randomRow);
        grid[randomColumn][randomRow] = "A";
        ships.add(A);
        
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
            if(grid[randomColumn][randomRow] == " "){
                column  = (char)(randomColumn+64);
                C1.getCoordinates(C1).add("" + column + randomRow);
                grid[randomColumn][randomRow] = "C1";
                ships.add(C1);
                empty = 1;
            }
        }

        //Way Cruiser's going
        //For the first cruiser, 1 direction can be occupied by the aircraft MAXIMUM
        if(grid[randomColumn][randomRow+1] == "A" || grid[randomColumn][randomRow+2] == "A"){
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
        else if (grid[randomColumn][randomRow-1] == "A" || grid[randomColumn][randomRow-2] == "A"){
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
        else if(grid[randomColumn-1][randomRow] == "A" || grid[randomColumn-2][randomRow] == "A"){
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
        else if(grid[randomColumn+1][randomRow] == "A" || grid[randomColumn+2][randomRow] == "A"){
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

        //2nd Cruiser's head
        empty = 0;
        Ship C2 = new Ship("C2", 3);
        while(empty == 0){
            randomColumn = ThreadLocalRandom.current().nextInt(3, 9);
            randomRow = ThreadLocalRandom.current().nextInt(3, 9);
            if(grid[randomColumn][randomRow] == " "){
                column  = (char)(randomColumn+64);
                C2.getCoordinates(C2).add("" + column + randomRow);
                grid[randomColumn][randomRow] = "C2";
                ships.add(C2);
                empty = 1;
            }
        }

        int dir[] = {1, 2, 3, 4};
        if(grid[randomColumn][randomRow+1] != " " || grid[randomColumn][randomRow+2] != " "){
            dir[0] = 0;
        }
        if(grid[randomColumn][randomRow-1] != " " || grid[randomColumn][randomRow-2] != " "){
            dir[1] = 0;
        }
        if(grid[randomColumn-1][randomRow] != " " || grid[randomColumn-2][randomRow] != " "){
            dir[2] = 0;
        }
        if(grid[randomColumn+1][randomRow] != " " || grid[randomColumn+2][randomRow] != " "){
            dir[3] = 0;
        }

        
        //here 0 means up, 1 down, 2 left, and 3 right 
        do{
            direction = ThreadLocalRandom.current().nextInt(0, 4);
        }while(dir[direction] == 0);

        switch (direction) {
            case 0:
                C2.getCoordinates(C2).add("" + column + randomRow+1); 
                grid[randomColumn][randomRow+1] = "C2";

                C2.getCoordinates(C2).add("" + column + randomRow+2); 
                grid[randomColumn][randomRow+2] = "C2";
                break;
            case 1:
                C2.getCoordinates(C2).add("" + column + (randomRow-1));
                grid[randomColumn][randomRow-1] = "C2";

                C2.getCoordinates(C2).add("" + column + (randomRow-2));
                grid[randomColumn][randomRow-2] = "C2";
                break;
            case 2:
                column = (char)(randomColumn+63);
                C2.getCoordinates(C2).add("" + column + randomRow);
                grid[randomColumn-1][randomRow] = "C2";

                column = (char)(randomColumn+62);
                C2.getCoordinates(C2).add("" + column + randomRow);
                grid[randomColumn-2][randomRow] = "C2";
                break;
            case 3:
                column = (char)(randomColumn+65);
                C2.getCoordinates(C2).add("" + column + randomRow);
                grid[randomColumn+1][randomRow] = "C2";

                column = (char)(randomColumn+66);
                C2.getCoordinates(C2).add("" + column + randomRow);
                grid[randomColumn+2][randomRow] = "C2";
                break;
            default:
                break;
        }
        
        //Destroyers making
        
        //1st Destroyer's head
        empty = 0;
        Ship D1 = new Ship("D1", 2);
        while(empty == 0){
            randomColumn = ThreadLocalRandom.current().nextInt(2, 10);
            randomRow = ThreadLocalRandom.current().nextInt(2, 10);
            if(grid[randomColumn][randomRow] == " "){
                column = (char)(randomColumn+64);
                D1.getCoordinates(D1).add("" + column + randomRow);
                grid[randomColumn][randomRow] = "D1";
                ships.add(D1);
                empty = 1;
            }
        }

        dir[0] = 1;
        dir[1] = 2;
        dir[2] = 3;
        dir[3] = 4;
        if(grid[randomColumn][randomRow+1] != " "){
            dir[0] = 0;
        }
        if(grid[randomColumn][randomRow-1] != " "){
            dir[1] = 0;
        }
        if(grid[randomColumn-1][randomRow] != " "){
            dir[2] = 0;
        }
        if(grid[randomColumn+1][randomRow] != " "){
            dir[3] = 0;
        }

        do{
            direction = ThreadLocalRandom.current().nextInt(0, 4);
        }while(dir[direction] == 0);

        switch (direction) {
            case 0:
                D1.getCoordinates(D1).add("" + column + randomRow+1); 
                grid[randomColumn][randomRow+1] = "D1";
                break;
            case 1:
                D1.getCoordinates(D1).add("" + column + (randomRow-1));
                grid[randomColumn][randomRow-1] = "D1";
                break;
            case 2:
                column = (char)(randomColumn+63);
                D1.getCoordinates(D1).add("" + column + randomRow);
                grid[randomColumn-1][randomRow] = "D1";
                break;
            case 3:
                column = (char)(randomColumn+65);
                D1.getCoordinates(D1).add("" + column + randomRow);
                grid[randomColumn+1][randomRow] = "D1";
                break;
            default:
                break;
        }

        //2nd Destroyer's head
        empty = 0;
        Ship D2 = new Ship("D2", 2);
        while(empty == 0){
            randomColumn = ThreadLocalRandom.current().nextInt(2, 10);
            randomRow = ThreadLocalRandom.current().nextInt(2, 10);
            if(grid[randomColumn][randomRow] == " "){
                column = (char)(randomColumn+64);
                D2.getCoordinates(D2).add("" + column + randomRow);
                grid[randomColumn][randomRow] = "D2";
                ships.add(D2);
                empty = 1;
            }
        }

        dir[0] = 1;
        dir[1] = 2;
        dir[2] = 3;
        dir[3] = 4;
        if(grid[randomColumn][randomRow+1] != " "){
            dir[0] = 0;
        }
        if(grid[randomColumn][randomRow-1] != " "){
            dir[1] = 0;
        }
        if(grid[randomColumn-1][randomRow] != " "){
            dir[2] = 0;
        }
        if(grid[randomColumn+1][randomRow] != " "){
            dir[3] = 0;
        }

        do{
            direction = ThreadLocalRandom.current().nextInt(0, 4);
        }while(dir[direction] == 0);

        switch (direction) {
            case 0:
                D2.getCoordinates(D2).add("" + column + randomRow+1); 
                grid[randomColumn][randomRow+1] = "D2";
                break;
            case 1:
                D2.getCoordinates(D2).add("" + column + (randomRow-1));
                grid[randomColumn][randomRow-1] = "D2";
                break;
            case 2:
                column = (char)(randomColumn+63);
                D2.getCoordinates(D2).add("" + column + randomRow);
                grid[randomColumn-1][randomRow] = "D2";
                break;
            case 3:
                column = (char)(randomColumn+65);
                D2.getCoordinates(D2).add("" + column + randomRow);
                grid[randomColumn+1][randomRow] = "D2";
                break;
            default:
                break;
        }


        //3rd Destroyer's head
        empty = 0;
        Ship D3 = new Ship("D3", 2);
        while(empty == 0){
            randomColumn = ThreadLocalRandom.current().nextInt(2, 10);
            randomRow = ThreadLocalRandom.current().nextInt(2, 10);
            if(grid[randomColumn][randomRow] == " "){
                column = (char)(randomColumn+64);
                D3.getCoordinates(D3).add("" + column + randomRow);
                grid[randomColumn][randomRow] = "D3";
                ships.add(D3);
                empty = 1;
            }
        }

        dir[0] = 1;
        dir[1] = 2;
        dir[2] = 3;
        dir[3] = 4;
        if(grid[randomColumn][randomRow+1] != " "){
            dir[0] = 0;
        }
        if(grid[randomColumn][randomRow-1] != " "){
            dir[1] = 0;
        }
        if(grid[randomColumn-1][randomRow] != " "){
            dir[2] = 0;
        }
        if(grid[randomColumn+1][randomRow] != " "){
            dir[3] = 0;
        }
        do{
            direction = ThreadLocalRandom.current().nextInt(0, 4);
        }while(dir[direction] == 0);
        switch (direction) {
            case 0:
                D3.getCoordinates(D3).add("" + column + randomRow+1); 
                grid[randomColumn][randomRow+1] = "D3";
                break;
            case 1:
                D3.getCoordinates(D3).add("" + column + (randomRow-1));
                grid[randomColumn][randomRow-1] = "D3";
                break;
            case 2:
                column = (char)(randomColumn+63);
                D3.getCoordinates(D3).add("" + column + randomRow);
                grid[randomColumn-1][randomRow] = "D3";
                break;
            case 3:
                column = (char)(randomColumn+65);
                D3.getCoordinates(D3).add("" + column + randomRow);
                grid[randomColumn+1][randomRow] = "D3";
                break;
            default:
                break;
            }


        //Torpedos making
        empty = 0;
        Ship T1 = new Ship("T1", 1);
        while(empty == 0){
            randomColumn = ThreadLocalRandom.current().nextInt(1, 11);
            randomRow = ThreadLocalRandom.current().nextInt(1, 11);
            if(grid[randomColumn][randomRow] == " "){
                column = (char)(randomColumn+64);
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
            if(grid[randomColumn][randomRow] == " "){
                column = (char)(randomColumn+64);
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
            if(grid[randomColumn][randomRow] == " "){
                column = (char)(randomColumn+64);
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
            if(grid[randomColumn][randomRow] == " "){
                column = (char)(randomColumn+64);
                T4.getCoordinates(T4).add("" + column + randomRow); 
                grid[randomColumn][randomRow] = "T4";
                ships.add(T4);
                empty = 1;
            }
        }
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
    
        else if (grid[row][column] == " " || grid[row][column] == "H"){
            incrementShotsFired();
            return "miss";
        }
        else{
            
            grid[row][column] = "H";
            incrementShotsFired();

            char col = (char)(column+64);
            
            for (Ship ship : ships) {
                if (ship.getCoordinates(ship).contains("" + col + row) ) {
                    ship.hit();
                    if (ship.isSunk()) {
                        return "sunk";
                    }
                    return "hit";
                }
            }
            return "Unexpected error in processing hit";
        }
    }

    /*private static class Coordinate {
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
    }*/

    // You may need additional methods for game logic, such as processing a fire command,
    // checking for a win, handling errors, etc.
}
