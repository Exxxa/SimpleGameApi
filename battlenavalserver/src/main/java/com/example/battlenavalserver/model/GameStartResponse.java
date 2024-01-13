package com.example.battlenavalserver.model;

// GameStartResponse.java

public class GameStartResponse {
    private String gameId;
    private String teamName;

    // Constructors, getters, and setters

    public GameStartResponse() {
    }

    public GameStartResponse(String gameId, String teamName) {
        this.gameId = gameId;
        this.teamName = teamName;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
