package com.example.battlenavalserver.controller;

import com.example.battlenavalserver.model.Game;
import com.example.battlenavalserver.service.GameService;
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
    public ResponseEntity<Game> startNewGame(@RequestParam String teamName, @RequestParam String suffix) {
        Game newGame = gameService.startNewGame(teamName, suffix);
        return ResponseEntity.ok(newGame);
    }

    @PostMapping("/fire")
    public ResponseEntity<String> fire(@RequestParam String gameId, @RequestParam int row, @RequestParam int col) {
        gameService.processFiring(gameId, row, col);
        return ResponseEntity.ok("Firing processed");
    }
}
