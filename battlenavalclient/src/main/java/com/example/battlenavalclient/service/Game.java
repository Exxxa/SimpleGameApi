// This class represents a Game in a naval battle system
package com.example.battlenavalclient.service;

public class Game {
    // Private fields to store the ID and team name of the game
    private String id;
    private String teamName;

    // Default constructor (no-argument constructor)
    public Game(){

    }

    // Parameterized constructor to initialize the Game with an ID and team name
    public Game(String id, String teamName){
        this.id = id;
        this.teamName = teamName;
    }

    // Getter method to retrieve the ID of the game
    public String getId(){
        return this.id;
    }

    // Getter method to retrieve the team name of the game
    public String getTeamName(){
        return this.teamName;
    }

    // Setter method to update the ID of the game
    public void setId(String id){
        this.id = id;
    }

    // Setter method to update the team name of the game
    public void setTeamName(String teamName){
        this.teamName = teamName;
    }
}
