package com.gdsc.game.model;

import com.gdsc.game.character.GameCharacter;
import lombok.Data;

@Data
public class CharacterDTO {
    private String name;
    private int hp;
    private int mp;
    private int defense;

    public CharacterDTO(GameCharacter character) {
        this.name = character.getName();
        this.hp = character.getHp();
        this.mp = character.getMp();
        this.defense = character.getDefense();
    }
}
