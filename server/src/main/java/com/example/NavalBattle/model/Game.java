package com.example.navalbattle.model;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.List;

import org.thymeleaf.standard.expression.Each;

public class Game {
    private final String gameId;
    private final String teamName;
    private final List<Ship> ships;
    private final char[][] grid;
    private int shotsFired;

    public Game(String gameId, String teamName) {
        this.gameId = gameId;
        this.teamName = teamName;
        this.ships = new ArrayList<>();
        this.grid = new char[10][10];
        initializeGrid();
        placeShips(); // You need to implement this method
        this.shotsFired = 0;
    }

    private void initializeGrid() {
        // Initialize the grid with empty cells
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = ' ';
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
        // - First determine randomlythe square where the head of the boat is
        // - Then determine randomly the way the boat is going:
        // meaning 1 up, 2 down, 3 left, 4 right

        //Aicraft making
        Ship A = new Ship("A", 4);
        ships.add(A);
        //Aircraft's head
        randomColumn = ThreadLocalRandom.current().nextInt(4, 8);
        randomRow = ThreadLocalRandom.current().nextInt(4, 8);
        char column = (char)(randomColumn+64);
        A.coordinates[0] = column + randomRow;
        grid[randomColumn][randomRow] = "A";
        
        //Way Aircraft's going
        int direction = ThreadLocalRandom.current().nextInt(1, 5);
        switch (direction) {
            case 1:
                A.coordinates[1] = column + randomRow;
                A.coordinates[2] = column + randomRow;
                A.coordinates[3] = column + randomRow;
                grid[randomColumn][randomRow+1] = "A";
                grid[randomColumn][randomRow+2] = "A";
                grid[randomColumn][randomRow+3] = "A";
                break;
            case 2:
                grid[randomColumn][randomRow-1] = "A";
                grid[randomColumn][randomRow-2] = "A";
                grid[randomColumn][randomRow-3] = "A";
                break;
            case 3:
                grid[randomColumn-1][randomRow] = "A";
                grid[randomColumn-2][randomRow] = "A";
                grid[randomColumn-3][randomRow] = "A";
                break;
            case 4:
                grid[randomColumn+1][randomRow] = "A";
                grid[randomColumn+2][randomRow] = "A";
                grid[randomColumn+3][randomRow] = "A";
                break;    
            default:
                break;
        }
        //Cruisers making
        Ship C1 = new Ship("C1", 3);
        Ship C2 = new Ship("C2", 3);
        ships.add(C1);
        ships.add(C2);
        
        //Destroyers making
        Ship D1 = new Ship("D1", 2);
        Ship D2 = new Ship("D2", 2);
        Ship D3 = new Ship("D3", 2);
        ships.add(D1);
        ships.add(D2);
        ships.add(D2);

        //Torpedos making
        Ship T1 = new Ship("T1", 1);
        Ship T2 = new Ship("T2", 1);
        Ship T3 = new Ship("T3", 1);
        ships.add(T1);
        ships.add(T2);
        ships.add(T2);

        //randomRow[0] = ThreadLocalRandom.current().nextInt(1, 11);
        //randomColumn[0] = ThreadLocalRandom.current().nextInt(1, 11);

        //Conversion to to char from int : (char)(a+64) (1 becomes A, 2 becomes B, ...)
    }

    public String getGameId() {
        return gameId;
    }

    public String getTeamName() {
        return teamName;
    }

    public char[][] getGrid() {
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

    // You may need additional methods for game logic, such as processing a fire command,
    // checking for a win, handling errors, etc.
}
