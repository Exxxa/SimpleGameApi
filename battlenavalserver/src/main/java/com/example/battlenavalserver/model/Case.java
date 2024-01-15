package com.example.battlenavalserver.model;

/**
 * Represents a single case on the game grid.
 */
public class Case {
    private int lign;              // Row index of the case
    private int column;            // Column index of the case
    private boolean isHit;         // Flag indicating if the case has been hit
    private Ship ship;             // Ship occupying the case, if any
    private ShotResult shotResult; // Result of a shot fired at this case

    /*
     * Constructor for creating a Case object.
     *
     * @param lign   Row index of the case.
     * @param column Column index of the case.
     */
    public Case(int lign, int column) {
        this.lign = lign;
        this.column = column;
        this.isHit = false;
        this.ship = null;
        this.shotResult = ShotResult.NONE;
    }

    /*
     * Gets the row index of the case.
     *
     * @return Row index.
     */
    public int getLign() {
        return lign;
    }

    /*
     * Sets the row index of the case.
     *
     * @param lign New row index.
     */
    public void setLign(int lign) {
        this.lign = lign;
    }

    /*
     * Gets the column index of the case.
     *
     * @return Column index.
     */
    public int getColumn() {
        return column;
    }

    /*
     * Sets the column index of the case.
     *
     * @param column New column index.
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /*
     * Checks if the case has been hit.
     *
     * @return True if the case has been hit, false otherwise.
     */
    public boolean isHit() {
        return isHit;
    }

    /*
     * Sets the hit status of the case and updates shot result based on the presence of a ship.
     *
     * @param hit True if the case is hit, false otherwise.
     */
    public void setHit(boolean hit) {
        isHit = hit;
        if (hit && this.ship != null) {
            shotResult = ship.isSunk() ? ShotResult.SUNK : ShotResult.HIT;
        } else {
            shotResult = ShotResult.MISS;
        }
    }

    /*
     * Gets the ship occupying the case.
     *
     * @return Ship object, or null if no ship is present.
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Sets the ship occupying the case.
     *
     * @param ship Ship object, or null if no ship is present.
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /*
     * Gets the result of a shot fired at this case.
     *
     * @return ShotResult enum representing the result of a shot.
     */
    public ShotResult getShotResult() {
        return shotResult;
    }

    /**
     * Sets the result of a shot fired at this case.
     *
     * @param shotResult ShotResult enum representing the result of a shot.
     */

    public void setShotResult(ShotResult shotResult) {
        this.shotResult = shotResult;
    }

    /*
     * Enum representing possible shot results.
     */
    public enum ShotResult {
        MISS, HIT, SUNK, NONE
    }
}
