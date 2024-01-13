package com.example.battlenavalserver.model;
// GameStartRequest.java

public class GameStartRequest {
    private String teamName;
    private String suffix; // Unique session identification parameter

    // Constructors, getters, and setters

    public GameStartRequest() {
    }

    public GameStartRequest(String teamName, String suffix) {
        this.teamName = teamName;
        this.suffix = suffix;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
