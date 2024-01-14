package com.example.battlenavalserver.model;


// Represents a ship in the game of Battleship
public class Ship {
    private ShipType type;
    private int size;
    private int hitCount;

    public Ship(ShipType type) {
        this.type = type;
        this.size = type.getSize();
        this.hitCount = 0;
    }

    public ShipType getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void hit() {
        hitCount++;
    }

    public boolean isSunk() {
        return hitCount == size;
    }

    public enum ShipType {
        AIRCRAFT_CARRIER(4),
        CRUISER(3),
        DESTROYER(2),
        TORPEDO_BOAT(1);

        private int size;

        ShipType(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }
}