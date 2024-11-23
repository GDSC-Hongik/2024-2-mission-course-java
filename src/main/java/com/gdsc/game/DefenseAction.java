package com.gdsc.game;

import java.util.Random;

public class DefenseAction implements Action {
    private static final int MIN_DEFENSE = 1;
    private static final int MAX_DEFENSE = 10;
    private final Random random = new Random();



    @Override
    public void execute(Character current, Character target) {
        int defense = random.nextInt(MAX_DEFENSE - MIN_DEFENSE + 1) + MIN_DEFENSE;
        current.setDefense(defense);
    }

}