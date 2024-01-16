package com.example.battlenavalclient.service;

public class Game {
    private String id;
    private String teamName;

    public Game(){

    }

    public Game(String id, String teamName){
        this.id = id;
        this.teamName = teamName;
    }

    public String getId(){
        return this.id;
    }

    public String getTeamName(){
        return this.teamName;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }
}
