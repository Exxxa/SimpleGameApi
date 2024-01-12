package com.example.NavalBattle.server.repository;
// GameRepository.java

import com.example.NavalBattle.server.model.Game;

import java.util.HashMap;
import java.util.Map;

public class GameRepository {
    private final Map<String, Game> games;

    public GameRepository() {
        this.games = new HashMap<>();
    }

    public void saveGame(Game game) {
        games.put(game.getGameId(), game);
    }

    public Game getGame(String gameId) {
        return games.get(gameId);
    }

    // Additional methods for game-related data operations can be added here
}