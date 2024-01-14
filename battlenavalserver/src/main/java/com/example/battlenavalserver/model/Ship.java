package com.example.battlenavalserver.model;

import java.util.List;

// Represents a ship in the game of Battleship
public class Ship {
    // Name of the ship
    private final String name;
    // Size of the ship (number of squares it occupies on the board)
    private final int size;
    // List of coordinates on the game board where the ship is located
    private List<String> coordinates;
    // Number of times the ship has been hit
    private int hits;

    // Constructor for the Ship class
    // Sets the name and size of the ship, and initializes the hits to 0
    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.hits = 0;
    }

    // Returns the name of the ship
    public String getName() {
        return this.name;
    }

    // Returns the size of the ship
    public int getSize() {
        return this.size;
    }

    // Returns the number of hits the ship has received
    public int getHits() {
        return this.hits;
    }

    // Returns the list of coordinates where the ship is located
    // Note: This method takes a Ship object as a parameter, but it is not used in the implementation
    public List<String> getCoordinates(Ship ship){
        return ship.coordinates;
    }

    // Adds a coordinate to the list of coordinates where the ship is located
    public void setCoordinates(String coordinates) {
        this.coordinates.add(coordinates);
    }

    // Increments the number of hits the ship has received
    public void hit() {
        this.hits++;
    }

    // Returns true if the ship has been sunk (i.e. the number of hits is greater than or equal to the size of the ship)
    public boolean isSunk() {
        return this.hits >= this.size;
    }

    // Returns the size of the ship
    public int getLength() {
        return this.size;
    }
}