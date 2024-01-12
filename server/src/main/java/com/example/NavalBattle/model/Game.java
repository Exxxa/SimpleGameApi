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
        // Aircraft carrier: 1* 4 conscutive squares
        // Cruisers: 2* Each occupies 3 squares
        // Destroyers: 3* Each occupies 2 squares
        // Torpedo Boats: 4* Each occupies 1 square
        int min = 1 ;
        int max = 10;
        int randomRow[] = new int[10];
        for (int i=0; i<10; i++){
            randomRow[i] = 0;
        }
        int randomColumn[] = new int[10];
        for (int i=0; i<10; i++){
            randomColumn[i] = 0;
        }
        randomColumn[0] = ThreadLocalRandom.current().nextInt(min, max + 1);
        randomColumn[0] = ThreadLocalRandom.current().nextInt(min, max + 1);

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
