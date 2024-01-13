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
        return this.name;
    }

    public int getSize() {
        return this.size;
    }

    public int getHits() {
        return this.hits;
    }

    public void hit() {
        this.hits++;
    }

    public boolean isSunk() {
        return this.hits >= this.size;
    }

    public int getLength() {
        return this.size;
    }
}
