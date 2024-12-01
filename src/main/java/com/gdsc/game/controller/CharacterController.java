package com.gdsc.game.controller;

import com.gdsc.game.service.CharacterService;
import com.gdsc.game.model.CharacterDTO;
import com.gdsc.game.model.SkillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/{name}")
    public CharacterDTO getCharacterStatus(@PathVariable String name) {
        return characterService.getCharacterStatus(name);
    }

    @GetMapping("/{name}/actions")
    public List<SkillDTO> getAvailableActions(
            @PathVariable String name,
            @RequestParam(defaultValue = "name") String sort) {
        return characterService.getAvailableActions(name, sort);
    }
}
