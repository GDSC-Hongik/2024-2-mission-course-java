package com.gdsc.game;

interface interfaceAction{
    Object[] attack();
    Object[] defense();
    void attack2();
    void attack3();
    void attackHard();
}

public class Action implements interfaceAction {

    String action;

    @Override
    public Object[] attack(){
        action = "attack";
        int damage = (int)(Math.random() * 10) + 1;
        Object[] values = {action,damage};
        return values;
    }

    @Override
    public Object[] defense(){
        action = "defense";
        int shield = (int)(Math.random() * 10) + 1;
        Object[] values = {action, shield};
        return values;

    }

    @Override
    public void attack2(){

    }

    @Override
    public void attack3(){

    }

    @Override
    public void attackHard(){

    }
}

