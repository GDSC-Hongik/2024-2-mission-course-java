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
        // this.behaviorList = new ArrayList<>();
    }

    // method: hp <= 0 이면 false 반환 (게임 종료)
    boolean isAlive() {
        return this.hp > 0;
    }

}