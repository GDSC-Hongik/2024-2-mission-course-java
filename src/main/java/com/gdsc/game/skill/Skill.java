package com.gdsc.game.skill;

import java.util.Random;

public class Skill implements SkillAction {
    private String name;
    private int manaCost;
    private int cooldown;
    private int currentCooldown;

    public Skill(String name, int manaCost, int cooldown) {
        this.name = name;
        this.manaCost = manaCost;
        // 쿨다운을 고정된 값으로 설정
        this.cooldown = cooldown;
        this.currentCooldown = 0; // 기본값은 0
    }

    @Override
    public boolean canUse(int mana) {
        // 현재 쿨다운이 0이거나 마나가 부족하지 않으면 사용 가능
        return currentCooldown == 0 && mana >= manaCost;
    }

    @Override
    public int use(int mana) {
        if (!canUse(mana)) return 0;
        currentCooldown = cooldown; // 스킬 사용 시 고정된 쿨다운 값 적용
        return manaCost * (new Random().nextInt(10) + 1);
    }

    @Override
    public void reduceCooldown() {
        if (currentCooldown > 0) currentCooldown--; // 쿨다운을 1턴씩 감소
    }

    @Override
    public String getInfo() {
        // 고정된 쿨다운 값을 출력
        return String.format("%s(%d ~ %d) - %dMP - %dturn", name, manaCost, manaCost * 10, manaCost, cooldown);
    }

    @Override
    public int getManaCost() {
        return manaCost;
    }
}