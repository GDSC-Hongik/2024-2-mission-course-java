package com.gdsc.game;

interface interfaceCharacter{

    void setName(String name);
    void losehp(int digit);
    void losemp(int digit);
    boolean alive();
}

public class Character implements interfaceCharacter{
    private String name;
    private int hp;
    private int mp;

    public Character(String name){
        this.name = name;
        this.hp = 100;
        this.mp = 10;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public String getName() {
        return name;
    }

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

    @Override
    public boolean alive(){
        if(this.hp>0) return true;
        else return false;
    }

}
