// Game.java
package com.example.NavalBattle.server.model;

import java.util.ArrayList;
import java.util.List;

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
