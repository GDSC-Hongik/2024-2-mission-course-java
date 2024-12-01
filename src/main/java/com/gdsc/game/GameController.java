package com.gdsc.game;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private final Game gameService;

    public GameController(Game gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/character/{name}/state")
    public Character getCharacterState(@PathVariable String name) {
        return gameService.getCharacterState(name);
    }


}
