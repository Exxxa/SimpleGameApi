package com.example.battlenavalserver.service;


import com.example.battlenavalserver.model.Case;
import com.example.battlenavalserver.model.Game;
import com.example.battlenavalserver.model.Ship;
import com.example.battlenavalserver.model.Ship.ShipType;
import com.example.battlenavalserver.repository.GameRepository;

public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game initializeGame(String gameId, String teamName) {

        Game game = new Game(gameId, teamName);
        placeShips(game);
        return game;
    }

    public Game getGameById(String gameId) {
        return gameRepository.getGame(gameId);
    }

    public void saveGame(Game game) {
        gameRepository.saveGame(game);
    }

    private static void placeShips(Game game) {
        // you can adjust these as needed
        game.placeShip(ShipType.AIRCRAFT_CARRIER, 0, 0, true);
        game.placeShip(ShipType.CRUISER, 2, 3, false);
        game.placeShip(ShipType.DESTROYER, 5, 5, true);
        game.placeShip(ShipType.TORPEDO_BOAT, 8, 2, false);
    }

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