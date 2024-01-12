package com.example.NavalBattle.client.service;

// GameService.java

import com.yourcompany.navalbattle.model.Game;
import com.yourcompany.navalbattle.model.Ship;

public class GameService {
    private static final int GRID_SIZE = 10;

    public static void fire(Game game, int row, int col) {
        char[][] grid = game.getGrid();

        if (row < 0 || row >= GRID_SIZE || col < 0 || col >= GRID_SIZE) {
            // Handle invalid coordinates
            return;
        }

        char cell = grid[row][col];

        if (cell == 'X' || cell == 'O') {
            // The cell has already been fired at
            return;
        }

        boolean hit = false;

        for (Ship ship : game.getShips()) {
            hit = fireAtShip(ship, row, col);

            if (hit) {
                grid[row][col] = 'X'; // Hit
                game.incrementShotsFired();
                if (ship.isSunk()) {
                    // Implement logic for handling a sunk ship
                }
                break;
            }
        }

        if (!hit) {
            grid[row][col] = 'O'; // Miss
            game.incrementShotsFired();
        }

        // Implement additional logic as needed
    }

    private static boolean fireAtShip(Ship ship, int
