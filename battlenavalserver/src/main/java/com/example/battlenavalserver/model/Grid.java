package com.example.battlenavalserver.model;

/**
 * Represents the game grid containing individual cases.
 */
public class Grid {
    private Case[][] grid; // 2D array representing the grid of cases

    /**
     * Constructor for creating a Grid object with a default size of 10x10.
     * Initializes the grid and populates it with Case objects.
     */
    public Grid() {
        this.grid = new Case[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = new Case(i, j);
            }
        }
    }

    /**
     * Gets the 2D array representing the grid of cases.
     *
     * @return The grid of cases.
     */
    public Case[][] getGrid() {
        return grid;
    }

    /**
     * Sets the 2D array representing the grid of cases.
     *
     * @param grid New grid of cases.
     */
    public void setGrid(Case[][] grid) {
        this.grid = grid;
    }
}
