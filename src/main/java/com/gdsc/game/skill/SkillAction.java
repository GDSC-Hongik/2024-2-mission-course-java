package com.gdsc.game.skill;

public interface SkillAction {
    boolean canUse(int mana);
    int use(int mana);
    void reduceCooldown();
    String getInfo();
    int getManaCost();
}
