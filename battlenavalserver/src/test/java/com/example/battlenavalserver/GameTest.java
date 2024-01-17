package com.example.battlenavalserver;

import com.example.battlenavalserver.model.Case;
import com.example.battlenavalserver.model.Game;
import com.example.battlenavalserver.model.Ship;
import com.example.battlenavalserver.model.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void isValidPlacement() {
        Game game = new Game("testGame", "testTeam");

        // Test a valid placement
        assertTrue(game.isValidPlacement(0, 0, 4, true));

        // Test an invalid placement due to overlapping
        game.placeShip(Ship.ShipType.AIRCRAFT_CARRIER, 2, 2, true);
        assertFalse(game.isValidPlacement(1, 1, 4, true));

        // Test an invalid placement due to exceeding grid boundaries
        assertFalse(game.isValidPlacement(9, 9, 4, true));
    }

    @Test
    void placeShip() {
        Game game = new Game("testGame", "testTeam");

        // Place a ship and check if it's on the grid
        game.placeShip(Ship.ShipType.AIRCRAFT_CARRIER, 0, 0, true);
        Grid grid = game.getGrid();
        Case[][] cases = grid.getGrid();
        assertNotNull(cases[0][0].getShip());
    }

    @Test
    void getGameId() {
        Game game = new Game("testGame", "testTeam");
        assertEquals("testGame", game.getGameId());
    }

    @Test
    void getTeamName() {
        Game game = new Game("testGame", "testTeam");
        assertEquals("testTeam", game.getTeamName());
    }

    @Test
    void getGrid() {
        Game game = new Game("testGame", "testTeam");
        assertNotNull(game.getGrid());
    }

    @Test
    void getShips() {
        Game game = new Game("testGame", "testTeam");
        assertNotNull(game.getShips());
    }

    @Test
    void getShotsFired() {
        Game game = new Game("testGame", "testTeam");
        assertEquals(0, game.getShotsFired());
    }

    @Test
    void incrementShotsFired() {
        Game game = new Game("testGame", "testTeam");
        game.incrementShotsFired();
        assertEquals(1, game.getShotsFired());
    }

    @Test
    void isGameOver() {
        Game game = new Game("testGame", "testTeam");
        assertFalse(game.isGameOver());
    }

    @Test
    void setGameOver() {
        Game game = new Game("testGame", "testTeam");
        game.setGameOver(true);
        assertTrue(game.isGameOver());
    }
}
