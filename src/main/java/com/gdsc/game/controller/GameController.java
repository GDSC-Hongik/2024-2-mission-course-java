package com.gdsc.game.controller;

import com.gdsc.game.model.ActionRequest;
import com.gdsc.game.model.GameStatus;
import com.gdsc.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/init")
    public GameStatus initializeGame(@RequestBody GameStatus request) {
        return gameService.initializeGame(request);
    }

    @PostMapping("/action")
    public GameStatus performAction(@RequestBody ActionRequest request) {
        return gameService.performAction(request);
    }

    @GetMapping("/status")
    public GameStatus getGameStatus() {
        return gameService.getGameStatus();
    }
}
