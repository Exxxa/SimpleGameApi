package com.example.battlenavalserver.model;

/**
 * Represents a ship in the game of Battleship.
 */
public class Ship {
    private ShipType type;   // Type of the ship (e.g., Aircraft Carrier, Cruiser)
    private int size;        // Size of the ship, determined by its type
    private int hitCount;    // Number of hits the ship has taken

    /**
     * Constructor for creating a Ship object of a specific type.
     *
     * @param type Type of the ship.
     */
    public Ship(ShipType type) {
        this.type = type;
        this.size = type.getSize();
        this.hitCount = 0;
    }

    /**
     * Gets the type of the ship.
     *
     * @return ShipType enum representing the type of the ship.
     */
    public ShipType getType() {
        return type;
    }

    /**
     * Gets the size of the ship.
     *
     * @return Size of the ship.
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the current hit count of the ship.
     *
     * @return Number of hits the ship has taken.
     */
    public int getHitCount() {
        return hitCount;
    }

    /**
     * Records a hit on the ship.
     */
    public void hit() {
        hitCount++;
    }

    /**
     * Checks if the ship is sunk based on its hit count.
     *
     * @return True if the ship is sunk, false otherwise.
     */
    public boolean isSunk() {
        return hitCount == size;
    }

    /**
     * Enum representing the possible types of ships.
     */
    public enum ShipType {
        AIRCRAFT_CARRIER(4),
        CRUISER(3),
        DESTROYER(2),
        TORPEDO_BOAT(1);

        private int size; // Size of the ship type

        /**
         * Constructor for ShipType enum with a specified size.
         *
         * @param size Size of the ship type.
         */
        ShipType(int size) {
            this.size = size;
        }

        /**
         * Gets the size of the ship type.
         *
         * @return Size of the ship type.
         */
        public int getSize() {
            return size;
        }
    }
}
