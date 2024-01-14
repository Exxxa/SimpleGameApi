package com.example.battlenavalserver.service;


import org.springframework.stereotype.Service;

import com.example.battlenavalserver.model.Game;
//import com.example.battlenavalserver.model.Ship;
import com.example.battlenavalserver.repository.GameRepository;
//import com.example.battlenavalserver.repository.ShipRepository;

//import java.util.ArrayList;
//import java.util.List;

@Service
public class GameService {
    private static final int GRID_SIZE = 10;

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game startNewGame(String teamName, String suffix) {
        String[][] grid = new String[GRID_SIZE][GRID_SIZE];
        Game newGame = new Game(teamName, suffix, grid, 0);
        gameRepository.saveGame(newGame);
        
        return newGame;
    }
}