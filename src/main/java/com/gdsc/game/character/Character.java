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

    // constructor
    public Character(String name, int hp, int mp) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.defense = 0;
        this.behaviorList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getDefense() {
        return defense;
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

    // method: hp <= 0 이면 false 반환 (게임 종료)
    boolean isAlive() {
        return this.hp > 0;
    }

}