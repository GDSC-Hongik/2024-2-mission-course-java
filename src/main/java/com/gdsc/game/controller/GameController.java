package com.gdsc.game.controller;

import com.gdsc.game.dto.ActionResponse;
import com.gdsc.game.dto.CharacterStatusResponse;
import com.gdsc.game.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/character/{name}")
    public CharacterStatusResponse getStatus(@PathVariable String name){
        return gameService.getStatus(name);
    }

    @GetMapping("/characters/{name}/actions")
    public List<ActionResponse> showActions(
            @PathVariable String name, @RequestParam String actionName
    ) {
        return gameService.showActions(name, actionName);
    }
}
