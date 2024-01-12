package com.example.NavalBattle.client.model;

public class GameRequest {
    private String teamName;
    private int row;
    private int col;

    // Constructors, getters, and setters

    public GameRequest() {
    }

    public GameRequest(String teamName, int row, int col) {
        this.teamName = teamName;
        this.row = row;
        this.col = col;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}