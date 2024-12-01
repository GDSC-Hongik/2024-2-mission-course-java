package com.gdsc.game.action;

import com.gdsc.game.player.Player;

public interface Action {

    String getName(); // 행동 이름 반환
    int execute(Player user, Player target); // 행동 실행

    int getMinDamage();
    int getMaxDamage();
}
