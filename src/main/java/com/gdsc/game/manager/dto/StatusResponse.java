package com.gdsc.game.manager.dto;

import com.gdsc.game.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatusResponse {
    private Player player;
    private int currentTurn;
    private int maxTurn;

    @Override
    public String toString() {
        int remainTurn =  maxTurn - currentTurn;
        return player.getName() + ", " + player.getTurn() + player.getSkillCooldowns() +  "남은 턴 : " + remainTurn;
    }
}
