package com.example.battlenavalserver.model;

import java.util.List;

public class Ship {
    private final String name;
    private final int size;
    private List<String> coordinates;
    private int hits;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.hits = 0;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getHits() {
        return hits;
    }

    public List<String> getCoordinates(Ship ship){
        return ship.coordinates;
    }

    public void hit() {
        hits++;
    }

    public boolean isSunk() {
        return hits >= size;
    }
}
