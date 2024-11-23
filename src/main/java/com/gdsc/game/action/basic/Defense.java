package com.gdsc.game.action.basic;

import com.gdsc.game.manager.ActionResult;
import com.gdsc.game.player.Player;

import java.util.Random;

public class Defense implements BasicAction {
    private static final int MIN_DAMAGE = 1;
    private static final int MAX_DAMAGE = 10;
    private String name;

    public Defense(String name) {
        this.name = name;
    }

    @Override
    public String getName() {return name;}

    public int getRandomDamage() {
        return new Random().nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
    }

    @Override
    public ActionResult execute(Player user, Player target) { // actionresult 반환 시 defense 값은 음수로 취해 보냄.
        int defense = getRandomDamage();
        return new ActionResult("Defense", -defense, user.getName() + "의 방어 선택! 방어 가능 데미지는 " + defense + "입니다.");
    }

    public int getMaxDamage() {return MAX_DAMAGE;}
    public int getMinDamage() {return MIN_DAMAGE;}
}
