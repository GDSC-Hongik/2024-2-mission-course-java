package com.gdsc.game;

interface interfaceAction{
    int attack();
    int defense();
    int attack2();
    int attack3();
    int attackHard();
}

public class Action implements interfaceAction {

    @Override
    public int attack(){
        int damage = (int)(Math.random() * 10) + 1;
        return damage;
    }

    @Override
    public int defense(){
        int shield = (int)(Math.random() * 10) + 1;
        return shield;

    }

    @Override
    public int attack2(){
        int damage = (int)(Math.random() * 19) + 2;
        return damage;

    }

    @Override
    public int attack3(){
        int damage = (int)(Math.random() * 28) + 3;
        return damage;

    }

    @Override
    public int attackHard(){
        int damage = (int)(Math.random() * 46) + 5;
        return damage;

    }
}

