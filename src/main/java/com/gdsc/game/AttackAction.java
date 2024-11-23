package com.gdsc.game;

import java.util.Random;
// 공격
public class AttackAction implements Action {
    private static final int MIN_DAMAGE = 1;
    private static final int MAX_DAMAGE = 10;
    private final Random random = new Random();

    @Override
    public void execute(Character current, Character target) {
        int demage = random.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
        target.takeDamage(demage);
    }

}