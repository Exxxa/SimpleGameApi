package com.example.battlenavalserver.service;


import com.example.battlenavalserver.model.Game;
import com.example.battlenavalserver.model.Ship;
import com.example.battlenavalserver.repository.GameRepository;
import com.example.battlenavalserver.repository.ShipRepository;

import java.util.ArrayList;
import java.util.List;

public class GameService {

    private static final int GRID_SIZE = 10;

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game startNewGame(String teamName, String suffix) {
        String[][] grid = new string[GRID_SIZE][GRID_SIZE];
        List<Ship> ships = Game.placeShips();

        Game newGame = new Game(teamName, suffix, ships, grid, 0);
        gameRepository.saveGame(newGame); 

        return newGame;
    }


	public void processFiring(String gameId, int row, int col) {
        try {
            Game game = gameRepository.getGameById(gameId);
            game.processFiring(row, col);
            gameRepository.saveGame(game); // Update the game state after firing
        } 
        else
            print
    }
}