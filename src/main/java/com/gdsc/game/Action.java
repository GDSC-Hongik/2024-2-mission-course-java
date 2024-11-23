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
        System.out.println("#:"+ damage); //test용
        return damage;
    }

    @Override
    public int defense(){
        int shield = (int)(Math.random() * 10) + 1;
        System.out.println("#:"+ shield); //test용
        return shield;

    }

    @Override
    public int attack2(){
        int damage = 2*((int)(Math.random() * 10) + 1);
        System.out.println("#:"+ damage); //test용
        return damage;

    }

    @Override
    public int attack3(){
        int damage = 3*((int)(Math.random() * 10) + 1);
        System.out.println("#:"+ damage); //test용
        return damage;

    }

    @Override
    public int attackHard(){
        int damage = 5*((int)(Math.random() * 10) + 1);
        System.out.println("#:"+ damage); //test용
        return damage;

    }
}

