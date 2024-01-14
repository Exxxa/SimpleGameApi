package com.example.battlenavalserver.model;

public class Grid {
    private Case[][] grid;

    public Grid() {
        this.grid = new Case[10][10]; 
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = new Case(i, j);
            }
        }
    }

    public Case[][] getGrid() {
        return grid;
    }

    public void setGrid(Case[][] grid) {
        this.grid = grid;
    }
}