package com.example.battlenavalserver.service;

import org.springframework.stereotype.Service;

import com.example.battlenavalserver.model.Case;
import com.example.battlenavalserver.model.Game;
import com.example.battlenavalserver.model.Ship;
import com.example.battlenavalserver.model.Ship.ShipType;
import com.example.battlenavalserver.repository.GameRepository;

/**
 * Service class responsible for managing game-related operations.
 */
@Service
public class GameService {

    private  GameRepository gameRepository;

    /**
     * Constructor for GameService class.
     */
    public GameService() {
        this.gameRepository = new GameRepository();
    }

    /**
     * Initializes a new game with the provided game ID and team name.
     * Places default ships on the grid.
     *
     * @param gameId    ID of the new game.
     * @param teamName  Name of the team playing the game.
     * @return The initialized game.
     */
    public Game initializeGame(String gameId, String teamName) {
        Game game = new Game(gameId, teamName);
        placeShips(game);
        saveGame(game);
        return game;
    }

    /**
     * Retrieves a game by its ID.
     *
     * @param gameId ID of the game to retrieve.
     * @return The retrieved Game object, or null if no such game exists.
     */
    public Game getGameById(String gameId) {
        return gameRepository.getGame(gameId);
    }

    /**
     * Saves the state of the provided game.
     *
     * @param game The game to be saved.
     */
    public void saveGame(Game game) {
        gameRepository.saveGame(game);
    }

    /**
     * Places default ships on the provided game's grid.
     *
     * @param game The game on which ships will be placed.
     */
    private static void placeShips(Game game) {
        // You can adjust these placements as needed
        game.placeShip(ShipType.AIRCRAFT_CARRIER, 0, 0, true);
        game.placeShip(ShipType.CRUISER, 2, 9, false);
        game.placeShip(ShipType.CRUISER, 2, 3, false);
        game.placeShip(ShipType.DESTROYER, 5, 5, true);
        game.placeShip(ShipType.DESTROYER, 4, 4, true);
        game.placeShip(ShipType.DESTROYER, 5, 6, true);
        game.placeShip(ShipType.TORPEDO_BOAT, 8, 2, false);
        game.placeShip(ShipType.TORPEDO_BOAT, 8, 2, false);
        game.placeShip(ShipType.TORPEDO_BOAT, 8, 2, false);
        game.placeShip(ShipType.TORPEDO_BOAT, 8, 2, false);
    }

    /**
     * Fires a shot at the specified coordinates in the provided game.
     *
     * @param game   The game in which the shot is fired.
     * @param lign   Row index of the target case.
     * @param column Column index of the target case.
     */
    public void fireAt(Game game, int lign, int column) {
        Case targetCase = game.getGrid().getGrid()[lign][column];

        // Check if the case has already been hit
        if (targetCase.isHit()) {
            System.out.println("This position has already been attacked. Choose a different one.");
            return;
        }
        // Mark the case as hit
        targetCase.setHit(true);
        // Check if there is a ship on the case
        Ship ship = targetCase.getShip();
        if (ship != null) {
            ship.hit();
            System.out.println("Hit!");
            if (ship.isSunk()) {
                System.out.println("You sunk a " + ship.getType() + "!");
            }
        } else {
            System.out.println("Miss!");
        }
    }
}
