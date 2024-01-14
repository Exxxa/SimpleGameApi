package com.example.battlenavalserver.repository;

import com.example.battlenavalserver.model.Game;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class GameRepository {
    /**
     * A map to store all the games in the repository.
     * The key is the game ID and the value is the Game object.
     */
    private final Map<String, Game> games;

    /**
     * Constructor for GameRepository class.
     * Initializes the games map.
     */
    public GameRepository() {
        this.games = new HashMap<>();
    }

    /**
     * Saves a game to the repository by adding it to the games map.
     *
     * @param game The game to be saved.
     */
    public void saveGame(Game game) {
        games.put(game.getGameId(), game); // add the game to the map
    }

    /**
     * Retrieves a game from the repository by looking up its ID in the games map.
     *
     * @param gameId The ID of the game to be retrieved.
     * @return The Game object associated with the given game ID, or null if no such game exists.
     */
    public Game getGame(String gameId) {
        return games.get(gameId); // look up the game in the map
    }
}