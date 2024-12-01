package com.gdsc.game.model;

import lombok.Data;

@Data
public class ActionRequest {
    private String attacker;
    private String defender;
    private String actionType; // "Attack", "Defend", "Skill"
    private Integer skillIndex; // Nullable
}
