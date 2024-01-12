package com.example.navalbattle.controller

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
        // Invoke the service to start a new game
        GameStartResponse response = gameService.startNewGame(startRequest);

        // Return the response to the client
        return ResponseEntity.ok(response);
    }

    @PostMapping("/fire")
    public ResponseEntity<GameResponse> fire(@RequestBody GameRequest fireRequest) {
        // Invoke the service to process the firing action
        GameResponse response = gameService.processFireAction(fireRequest);

        // Return the response to the client
        return ResponseEntity.ok(response);
    }

    @PostMapping("/stop")
    public ResponseEntity<GameStopResponse> stopGame(@RequestBody GameStopRequest stopRequest) {
        // Invoke the service to stop the game
        GameStopResponse response = gameService.stopGame(stopRequest);

        // Return the response to the client
        return ResponseEntity.ok(response);
    }
}
