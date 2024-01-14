package com.example.battlenavalserver.model;

public class Case {
    private int lign;
    private int column;
    private boolean isHit;
    private Ship ship;
    private ShotResult shotResult;

    public Case(int lign, int column) {
        this.lign = lign;
        this.column = column;
        this.isHit = false;
        this.ship = null;
        this.shotResult = ShotResult.NONE;
    }

    public int getLign() {
        return lign;
    }

    public void setLign(int lign) {
        this.lign = lign;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
        if (hit && this.ship != null) {
            shotResult = ship.isSunk() ? ShotResult.SUNK : ShotResult.HIT;
        } else {

            shotResult = ShotResult.MISS;
        }
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public ShotResult getShotResult() {
        return shotResult;
    }

    public void setShotResult(ShotResult shotResult) {
        this.shotResult = shotResult;
    }

    public enum ShotResult {
        MISS, HIT, SUNK, NONE
    }
}