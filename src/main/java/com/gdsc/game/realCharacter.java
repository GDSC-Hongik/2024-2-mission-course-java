package com.gdsc.game;

interface Character{

    void losehp(int digit);
    void losemp(int digit);
}

public class realCharacter implements Character {
    String name;
    int hp;
    int mp;

    @Override
    public void losehp(int digit){
        this.hp -= digit;
    }

    @Override
    public void losemp(int digit) {
        this.mp -= digit;
    }
}
