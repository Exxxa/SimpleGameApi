package com.example.battlenavalserver.controller;

import com.example.battlenavalserver.model.Game;
import com.example.battlenavalserver.service.GameService;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startGame(@RequestParam(value = "teamName", defaultValue = "Debugging-gorillas") String teamName) {
        String gameId = UUID.randomUUID().toString(); // Generate a unique game ID
        gameService.initializeGame(gameId, teamName);
        return ResponseEntity.ok("Game started with ID: " + gameId);
    }

    @PostMapping("/{gameId}/fire")
    public ResponseEntity<String> fireAt(
            @PathVariable String gameId,
            @RequestParam int lign,
            @RequestParam int column) {
        Game game = gameService.getGameById(gameId);
        if (game == null) {
            System.out.println("Game with ID " + gameId + " not found.");
            return ResponseEntity.notFound().build();
            }
    
        gameService.fireAt(game, lign, column);
         return ResponseEntity.ok("Attack at (" + lign + ", " + column + ") in game " + gameId + " processed.");
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable String gameId) {
        Game game = gameService.getGameById(gameId);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }
}