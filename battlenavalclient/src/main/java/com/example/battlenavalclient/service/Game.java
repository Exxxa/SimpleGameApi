package com.example.battlenavalclient.service;

public class Game {
    private String ID;
    private String teamName;

    public Game(String ID, String teamName){
        this.ID = ID;
        this.teamName = teamName;
    }

    public String getID(){
        return this.ID;
    }

    public String getTeamName(){
        return this.teamName;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }
}
