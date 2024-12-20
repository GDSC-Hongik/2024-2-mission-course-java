package com.gdsc.game.domain;

import java.util.Random;

public abstract class SkillAction implements Action {
    protected final int manaCost;
    protected final int coolTime;
    protected int remainingCooltime;

    protected final Random random = new Random();

    public SkillAction(int manaCost, int coolTime) {
        this.manaCost = manaCost;
        this.coolTime = coolTime;
        remainingCooltime = 0;
    }

    public void reduceCoolTime() {
        if (remainingCooltime > 0) {
            remainingCooltime--;
        }
    }
}
