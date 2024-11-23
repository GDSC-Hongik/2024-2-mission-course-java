package com.gdsc.game.character;

import com.gdsc.game.behavior.Behavior;

import java.util.ArrayList;
import java.util.List;

public class Character {
    String name;
    int hp;
    int mp;
    int defense;
    List<Behavior> behaviorList;
    private int turnsUntilSkillReady;

    // constructor
    public Character(String name, int hp, int mp) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.defense = 0;
        this.behaviorList = new ArrayList<>();
        this.turnsUntilSkillReady = 0;
    }

    // getter & setter
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public List<Behavior> getBehaviorList() {
        return behaviorList;
    }

    public void setBehaviorList(List<Behavior> behaviorList) {
        this.behaviorList = behaviorList;
    }

    public int getTurnsUntilSkillReady() {
        return turnsUntilSkillReady;
    }

    public void setTurnsUntilSkillReady(int turnsUntilSkillReady) {
        this.turnsUntilSkillReady = turnsUntilSkillReady;
    }

    // method: skill 사용 여부를 관리
    public void decreaseTurnsUntilSkillReady() {
        if (turnsUntilSkillReady > 0) {
            turnsUntilSkillReady--;
        }
    }

    public boolean canUseSkill() {
        return turnsUntilSkillReady == 0;
    }

    // method: hp <= 0 이면 false 반환 (게임 종료)
    public boolean isAlive() {
        return this.hp > 0;
    }

}