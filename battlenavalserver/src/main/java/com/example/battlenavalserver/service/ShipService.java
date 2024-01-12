package com.example.battlenavalserver.service;

import com.example.battlenavalserver.model.Ship;

public class ShipService {
    public static void placeShip(Ship ship, char[][] grid, int startRow, int startCol, boolean isVertical) {
        int size = ship.getSize();

        if (isVertical) {
            for (int i = 0; i < size; i++) {
                grid[startRow + i][startCol] = 'S'; // 'S' represents a ship
            }
        } else {
            for (int i = 0; i < size; i++) {
                grid[startRow][startCol + i] = 'S';
            }
        }
    }

    // Additional methods for ship-related logic can be added here
}