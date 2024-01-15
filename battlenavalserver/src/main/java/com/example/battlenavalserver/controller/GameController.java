package com.example.battlenavalserver.controller;

import com.example.battlenavalserver.model.Game;
import com.example.battlenavalserver.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<String> startGame(@RequestParam(value = "teamName", defaultValue = "Debugging-gorillas") String teamName, @RequestParam(value = "gameID") String gameID) {
        gameService.initializeGame(gameID, teamName);
        return ResponseEntity.ok("Game started with ID: " + gameID);
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
    @GetMapping("/")
    public ResponseEntity<String> test() {
        
        return ResponseEntity.ok("test");
    }
}
