package com.example.battlenavalserver.controller;

import com.example.battlenavalserver.model.Game;
import com.example.battlenavalserver.model.Case.ShotResult;
import com.example.battlenavalserver.service.GameService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<Game> startGame(@RequestParam(value = "teamName", defaultValue = "Debugging-gorillas") String teamName) {
        String gameId = UUID.randomUUID().toString(); 
        Game game = gameService.initializeGame(gameId, teamName);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/{gameId}/fire")
    public ResponseEntity<ShotResult> fireAt(   
            @PathVariable String gameId,
            @RequestParam int lign,
            @RequestParam int column) {
        Game game = gameService.getGameById(gameId);
        /*if (game == null) {
            System.out.println("Game with ID " + gameId + " not found.");
            return ResponseEntity.notFound().build();
            }
    */
        ShotResult responseMessage =gameService.fireAt(game, lign, column);
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/{gameId}/id")
    public ResponseEntity<Game> getGame(@PathVariable String gameId) {
        Game game = gameService.getGameById(gameId);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }
    @GetMapping("/{teamName}/team")
    public ResponseEntity<Game> getGameByteamName(@PathVariable String teamName) {
        Game game = gameService.getGameByteamName(teamName);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }

    @GetMapping("/")
    public ResponseEntity<String> test() {
        
        return ResponseEntity.ok("Please go to http://localhost:8080/game/start to start a new game !");
    }
}
