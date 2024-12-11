package com.gdsc.game.service;

import com.gdsc.game.character.GameCharacter;
import com.gdsc.game.model.CharacterDTO;
import com.gdsc.game.model.SkillDTO;
import com.gdsc.game.game.GameManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    private final GameManager gameManager = new GameManager();

    public CharacterDTO getCharacterStatus(String name) {
        GameCharacter character = gameManager.getCharacter(name);
        return new CharacterDTO(character);
    }

    public List<SkillDTO> getAvailableActions(String name, String sort) {
        return gameManager.getAvailableActions(name, sort);
    }
}
