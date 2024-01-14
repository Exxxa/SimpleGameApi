package com.example.battlenavalserver.repository;
// ShipRepository.java

import com.example.battlenavalserver.model.Ship;

import java.util.ArrayList;
import java.util.List;

// ShipRepository class is responsible for managing the collection of Ship objects
public class ShipRepository {
    // ships is a list of Ship objects that will store all the ships in the game
    private final List<Ship> ships;

    // Constructor for ShipRepository class
    public ShipRepository() {
        // Initialize ships list
        this.ships = new ArrayList<>();
    }

    // saveShip method is used to add a new Ship object to the ships list
    public void saveShip(Ship ship) {
        ships.add(ship);
    }

    // getAllShips method returns a new list of all the Ship objects in the ships list
    public List<Ship> getAllShips() {
        // Return a new list of ships to avoid exposing the internal state of the repository
        return new ArrayList<>(ships);
    }

    // Additional methods for ship-related data operations can be added here
}