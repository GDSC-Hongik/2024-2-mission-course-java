package com.gdsc.game.model;

import com.gdsc.game.skill.Skill;
import lombok.Data;

@Data
public class SkillDTO {
    private String name;
    private int manaCost;
    private int cooldown;
    private int currentCooldown;

    public SkillDTO(Skill skill) {
        this.name = skill.getName();
        this.manaCost = skill.getManaCost();
        this.cooldown = skill.getCooldown();
        this.currentCooldown = skill.getCurrentCooldown();
    }
}

