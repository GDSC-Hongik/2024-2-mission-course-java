package com.gdsc.game;

interface interfaceCharacter{

    void setName(String name);
    void losehp(int digit);
    void losemp(int digit);
}

public class Character implements interfaceCharacter{
    String name;
    private int hp;
    private int mp;

    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public void losehp(int digit){
        this.hp -= digit;
    }

    @Override
    public void losemp(int digit) {
        this.mp -= digit;
    }

}