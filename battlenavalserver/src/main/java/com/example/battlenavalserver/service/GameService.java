package com.example.battlenavalserver.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.battlenavalserver.model.Case;
import com.example.battlenavalserver.model.Game;
import com.example.battlenavalserver.model.Ship;
import com.example.battlenavalserver.model.Case.ShotResult;
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

    public Game getGameByteamName(String teamName) {
        return gameRepository.getGame(teamName);
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
        
        int[] shipSizes = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        Random random = new Random();
        int randomLign = random.nextInt(10);
        int randomColumn = random.nextInt(10);
        boolean direction = random.nextBoolean();
        

        while(!game.isValidPlacement(randomLign, randomColumn, shipSizes[0], direction)){
            randomLign = random.nextInt(10);
            randomColumn = random.nextInt(10);
            direction = random.nextBoolean();  
        }
        game.placeShip(ShipType.AIRCRAFT_CARRIER, randomLign, randomColumn, direction);

        randomLign = random.nextInt(10);
        randomColumn = random.nextInt(10);
        direction = random.nextBoolean();
        while(!game.isValidPlacement(randomLign, randomColumn, shipSizes[1], direction)){
            randomLign = random.nextInt(10);
            randomColumn = random.nextInt(10);
            direction = random.nextBoolean();
        }
        game.placeShip(ShipType.CRUISER, randomLign, randomColumn, direction);

        randomLign = random.nextInt(10);
        randomColumn = random.nextInt(10);
        direction = random.nextBoolean();
        while(!game.isValidPlacement(randomLign, randomColumn, shipSizes[2], direction)){
            randomLign = random.nextInt(10);
            randomColumn = random.nextInt(10);
            direction = random.nextBoolean();
        }
        game.placeShip(ShipType.CRUISER, randomLign, randomColumn, direction);

        randomLign = random.nextInt(10);
        randomColumn = random.nextInt(10);
        direction = random.nextBoolean();
        while(!game.isValidPlacement(randomLign, randomColumn, shipSizes[3], direction)){
            randomLign = random.nextInt(10);
            randomColumn = random.nextInt(10);
            direction = random.nextBoolean(); 
        }
        game.placeShip(ShipType.DESTROYER, randomLign, randomColumn, direction); 

        randomLign = random.nextInt(10);
        randomColumn = random.nextInt(10);
        direction = random.nextBoolean();
        while(!game.isValidPlacement(randomLign, randomColumn, shipSizes[4], direction)){
            randomLign = random.nextInt(10);
            randomColumn = random.nextInt(10);
            direction = random.nextBoolean();
        }
        game.placeShip(ShipType.DESTROYER, randomLign, randomColumn, direction);

        randomLign = random.nextInt(10);
        randomColumn = random.nextInt(10);
        direction = random.nextBoolean();
        while(!game.isValidPlacement(randomLign, randomColumn, shipSizes[5], direction)){
            randomLign = random.nextInt(10);
            randomColumn = random.nextInt(10);
            direction = random.nextBoolean();  
        }
        game.placeShip(ShipType.DESTROYER, randomLign, randomColumn, direction);

        randomLign = random.nextInt(10);
        randomColumn = random.nextInt(10);
        direction = random.nextBoolean();
        while(!game.isValidPlacement(randomLign, randomColumn, shipSizes[6], direction)){
            randomLign = random.nextInt(10);
            randomColumn = random.nextInt(10);
            direction = random.nextBoolean();
        }
        game.placeShip(ShipType.TORPEDO_BOAT, randomLign, randomColumn, direction);

        randomLign = random.nextInt(10);
        randomColumn = random.nextInt(10);
        direction = random.nextBoolean();
        while(!game.isValidPlacement(randomLign, randomColumn, shipSizes[7], direction)){
            randomLign = random.nextInt(10);
            randomColumn = random.nextInt(10);
            direction = random.nextBoolean();
        }
        game.placeShip(ShipType.TORPEDO_BOAT, randomLign, randomColumn, direction);   

        randomLign = random.nextInt(10);
        randomColumn = random.nextInt(10);
        direction = random.nextBoolean();
        while(!game.isValidPlacement(randomLign, randomColumn, shipSizes[8], direction)){
            randomLign = random.nextInt(10);
            randomColumn = random.nextInt(10);
            direction = random.nextBoolean();
        }
        game.placeShip(ShipType.TORPEDO_BOAT, randomLign, randomColumn, direction);

        randomLign = random.nextInt(10);
        randomColumn = random.nextInt(10);
        direction = random.nextBoolean();
        while(!game.isValidPlacement(randomLign, randomColumn, shipSizes[9], direction)){
            randomLign = random.nextInt(10);
            randomColumn = random.nextInt(10);
            direction = random.nextBoolean();  
        }
        game.placeShip(ShipType.TORPEDO_BOAT, randomLign, randomColumn, direction);
    }

    /**
     * Fires a shot at the specified coordinates in the provided game.
     *
     * @param game   The game in which the shot is fired.
     * @param lign   Row index of the target case.
     * @param column Column index of the target case.
     */
    public ShotResult fireAt(Game game, int lign, int column) {
        Case targetCase = game.getGrid().getGrid()[lign][column];

        // Check if the case has already been hit
        if (targetCase.isHit()) {
            return ShotResult.MISS;
        }
        // Mark the case as hit
        targetCase.setHit(true);
        // Check if there is a ship on the case
        Ship ship = targetCase.getShip();
        if (ship != null) {
            ship.hit();
            if (ship.isSunk()) {
                return ShotResult.SUNK; //targetCase.getShotResult();
            } else {
                return ShotResult.HIT;
            }
        } else {
            return ShotResult.MISS;
        }
    }
}
