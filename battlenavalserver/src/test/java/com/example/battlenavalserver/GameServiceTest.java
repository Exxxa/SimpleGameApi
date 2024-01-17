package com.example.battlenavalserver;

import com.example.battlenavalserver.model.Case;
import com.example.battlenavalserver.model.Game;
import com.example.battlenavalserver.model.Ship;
import com.example.battlenavalserver.model.Ship.ShipType;
import com.example.battlenavalserver.model.Case.ShotResult;
import com.example.battlenavalserver.repository.GameRepository;
import com.example.battlenavalserver.service.GameService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameServiceTest {

    @Test
    void initializeGameShouldCreateAndSaveGame() {
        GameRepository mockRepository = mock(GameRepository.class);
        GameService gameService = new GameService();

        String gameId = "testGameId";
        String teamName = "testTeamName";

        Game initializedGame = gameService.initializeGame(gameId, teamName);

        assertNotNull(initializedGame);
        assertEquals(gameId, initializedGame.getGameId());
        assertEquals(teamName, initializedGame.getTeamName());

        verify(mockRepository, times(1)).saveGame(initializedGame);
    }

    @Test
    void getGameByIdShouldRetrieveGameFromRepository() {
        GameRepository mockRepository = mock(GameRepository.class);
        GameService gameService = new GameService();

        String gameId = "testGameId";
        Game expectedGame = new Game(gameId, "testTeamName");

        when(mockRepository.getGame(gameId)).thenReturn(expectedGame);

        Game retrievedGame = gameService.getGameById(gameId);

        assertNotNull(retrievedGame);
        assertEquals(expectedGame, retrievedGame);
    }

    @Test
    void saveGameShouldSaveGameToRepository() {
        GameRepository mockRepository = mock(GameRepository.class);
        GameService gameService = new GameService();

        Game gameToSave = new Game("testGameId", "testTeamName");

        gameService.saveGame(gameToSave);

        verify(mockRepository, times(1)).saveGame(gameToSave);
    }

    @Test
    void fireAtShouldHitOrMissAndSaveGame() {
        GameRepository mockRepository = mock(GameRepository.class);
        GameService gameService = new GameService();

        Game testGame = new Game("testGameId", "testTeamName");
        gameService.saveGame(testGame);

        int lign = 0;
        int column = 0;

        ShotResult shotResult = gameService.fireAt(testGame, lign, column);

        assertTrue(shotResult == ShotResult.HIT || shotResult == ShotResult.MISS);

        verify(mockRepository, times(1)).saveGame(testGame);
    }
}
