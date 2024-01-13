// GameService.java
package com.example.battlenavalserver.service;

import com.example.battlenavalserver.model.Game;
import com.example.battlenavalserver.model.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameService {

    private static final int GRID_SIZE = 10;
    private static final int NUM_SHIPS = 10;

    public static Game startNewGame(String teamName, String suffix) {
        char[][] grid = new char[GRID_SIZE][GRID_SIZE];
        initializeGrid(grid);

        List<Ship> ships = placeShipsOnGrid(grid);

        return new Game(teamName, suffix, ships, grid, 0);
    }

    public static void fire(Game game, int row, int col) {
        // Existing fire logic...
    }

    private static void initializeGrid(char[][] grid) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = ' '; // Empty cell
            }
        }
    }

    private static List<Ship> placeShipsOnGrid(char[][] grid) {
        List<Ship> ships = new ArrayList<>();

        // Implement logic to randomly place ships on the grid
        // For simplicity, let's place one ship at a fixed location for now
        Ship ship = new Ship("Aircraft Carrier", 4, 0, 0, true);
        placeShip(grid, ship);
        ships.add(ship);

        // Add logic to place other types of ships...

        return ships;
    }

    private static void placeShip(char[][] grid, Ship ship) {
        int length = ship.getLength();

        if (ship.isHorizontal()) {
            for (int i = 0; i < length; i++) {
                grid[ship.getRow()][ship.getCol() + i] = 'S'; // 'S' represents a ship
            }
        } else {
            for (int i = 0; i < length; i++) {
                grid[ship.getRow() + i][ship.getCol()] = 'S'; // 'S' represents a ship
            }
        }
    }
}
