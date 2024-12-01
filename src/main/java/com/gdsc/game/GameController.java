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

    @GetMapping("/character/{name}/skill")
    public String getAvailableSkill(@PathVariable String name){
        return gameService.getAvailableSKill(name);
    }



    @PostMapping("/action")
    public String performAction(@RequestBody ActionRequest request) {
        System.out.println(request.getName());
        System.out.println(request.getNumber());
        return gameService.performAction(request.getName(), request.getNumber());
    }
    public static class ActionRequest {
        private String name;
        private int number;

        public String getName() {
            return name;
        }

        public int getNumber() {
            return number;
        }

    }

}
