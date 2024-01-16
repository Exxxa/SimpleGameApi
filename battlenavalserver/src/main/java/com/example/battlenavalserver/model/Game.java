package com.example.battlenavalserver.model;

import com.example.battlenavalserver.model.Ship.ShipType;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final String gameId;
    private final String teamName;
    private List<Ship> ships;
    private Grid grid;
    private int shotsFired;
    private boolean isGameOver;

    public Game(String gameId, String teamName) {
        this.isGameOver = false;
        this.gameId = gameId;
        this.teamName = teamName;
        this.ships = new ArrayList<>();
        this.grid = new Grid();
        this.ships = getShips();
        this.shotsFired = 0;
    }

    public boolean isValidPlacement(int startLign, int startColumn, int size, boolean isHorizontal) {
        // Check if the ship fits within the grid boundaries
        if (isHorizontal && startColumn + size > 10) {
            return false;
        } else if (!isHorizontal && startLign + size > 10) {
            return false;
        }

        // Check for overlapping with existing ships
        for (int i = 0; i < size; i++) {
            int lign = isHorizontal ? startLign : startLign + i;
            int column = isHorizontal ? startColumn + i : startColumn;

            Case gridCase = grid.getGrid()[lign][column];
            if (gridCase.getShip() != null) {
                return false; // Overlapping with an existing ship
            }
        }

        return true;
    }
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

        public void placeShip(ShipType shipType, int startLign, int startColumn, boolean isHorizontal) {
            int size = shipType.getSize();
    
            // Check if the placement is valid
            if (!isValidPlacement(startLign, startColumn, size, isHorizontal)) {
                //FAUT IL METTRE CE PRINT ??
                System.out.println("Invalid placement. Please choose different coordinates.");
                return;
            }
    
            // Create a new ship
            Ship ship = new Ship(shipType);
    
            // Place the ship on the grid
            for (int i = 0; i < size; i++) {
                int lign = isHorizontal ? startLign : startLign + i;
                int column = isHorizontal ? startColumn + i : startColumn;
    
                Case gridCase = grid.getGrid()[lign][column];
                gridCase.setShip(ship);
            }
        }

    public String getGameId() {
        return  this.gameId;
    }

    public String getTeamName() {
        return  this.teamName;
    }

    public Grid getGrid() {
        return this.grid;
    }

    public List<Ship> getShips() {
        return  this.ships;
    }

    public int getShotsFired() {
        return  this.shotsFired;
    }

    public void incrementShotsFired() {
        this.shotsFired++;
    }

    public boolean isGameOver() {
        return  this.isGameOver;
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

}