package com.example.battlenavalclient.model;
// GameResponse.java
// This class represents the response from the server after a move is made in the game.
// It contains the result of the move ('hit', 'miss', or 'sunk'),
// the status of the targeted ship, and the updated game grid.

public class GameResponse {
    // The result of the move ('hit', 'miss', or 'sunk')
    private String result;

    // The status of the targeted ship
    private String shipStatus;

    // The updated game grid after the move
    private char[][] updatedGrid;

    // Default constructor
    public GameResponse() {
    }

    // Constructor with all fields
    public GameResponse(String result, String shipStatus, char[][] updatedGrid) {
        this.result = result;
        this.shipStatus = shipStatus;
        this.updatedGrid = updatedGrid;
    }

    // Getter for the result
    public String getResult() {
        return result;
    }

    // Setter for the result
    public void setResult(String result) {
        this.result = result;
    }

    // Getter for the ship status
    public String getShipStatus() {
        return shipStatus;
    }

    // Setter for the ship status
    public void setShipStatus(String shipStatus) {
        this.shipStatus = shipStatus;
    }

    // Getter for the updated grid
    public char[][] getUpdatedGrid() {
        return updatedGrid;
    }

    // Setter for the updated grid
    public void setUpdatedGrid(char[][] updatedGrid) {
        this.updatedGrid = updatedGrid;
    }
}