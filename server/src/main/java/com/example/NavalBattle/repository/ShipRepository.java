package com.example.NavalBattle.repository;
// ShipRepository.java

import com.example.NavalBattle.model.Ship;

import java.util.ArrayList;
import java.util.List;

public class ShipRepository {
    private final List<Ship> ships;

    public ShipRepository() {
        this.ships = new ArrayList<>();
    }

    public void saveShip(Ship ship) {
        ships.add(ship);
    }

    public List<Ship> getAllShips() {
        return new ArrayList<>(ships);
    }

    // Additional methods for ship-related data operations can be added here
}
