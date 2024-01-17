package com.example.battlenavalserver;

import com.example.battlenavalserver.controller.GameController;
import com.example.battlenavalserver.model.Game;
import com.example.battlenavalserver.model.Case.ShotResult;
import com.example.battlenavalserver.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Test
    void startGame() throws Exception {
        when(gameService.initializeGame(anyString(), anyString())).thenReturn(new Game("teamName", "TestTeam"));
        mockMvc.perform(MockMvcRequestBuilders.post("/game/start")
                .param("teamName", "TestTeam"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void fireAt() throws Exception {
        when(gameService.getGameById(anyString())).thenReturn(new Game("TestTeam", "TestTeam"));
        when(gameService.fireAt(any(Game.class), anyInt(), anyInt())).thenReturn(ShotResult.HIT);
        mockMvc.perform(MockMvcRequestBuilders.post("/game/TestTeam/fire")
                .param("lign", "1")
                .param("column", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void getGame() throws Exception {
        when(gameService.getGameById(anyString())).thenReturn(new Game("TestTeam", null));
        mockMvc.perform(MockMvcRequestBuilders.get("/game/TestTeam"))
                .andExpect(status().isNotFound());
    }


}
