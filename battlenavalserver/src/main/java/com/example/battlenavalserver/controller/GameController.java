package com.example.battlenavalserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.battlenavalserver.model.GameStartRequest;
import com.example.battlenavalserver.model.GameStartResponse;
import com.example.battlenavalserver.service.GameService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// GameController.java
@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity<GameStartResponse> startGame(@RequestBody GameStartRequest startRequest) {
        // Validate startRequest and handle any initialization logic
        // ...

        // Invoke the service to start a new game
        GameStartResponse response = gameService.startNewGame(startRequest);

        // Return the response to the client
        return ResponseEntity.ok(response);
    }

    @PostMapping("/fire")
    public ResponseEntity<GameResponse> fire(@RequestBody GameFireRequest fireRequest) {
        // Validate fireRequest and handle firing logic
        // ...

        // Invoke the service to process the firing action
        GameResponse response = gameService.processFireAction(fireRequest);

        // Return the response to the client
        return ResponseEntity.ok(response);
    }

    @PostMapping("/stop")
    public ResponseEntity<GameStopResponse> stopGame(@RequestBody GameStopRequest stopRequest) {
        // Validate stopRequest and handle game termination logic
        // ...

        // Invoke the service to stop the game
        GameStopResponse response = gameService.stopGame(stopRequest);

        // Return the response to the client
        return ResponseEntity.ok(response);
    }
}
