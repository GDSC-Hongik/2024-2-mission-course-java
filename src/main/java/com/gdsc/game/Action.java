package com.gdsc.game;

interface interfaceAction{
    int attack();
    int defense();
    int attack2(Character A);
    int attack3(Character A);
    int attackHard(Character A);
}

public class Action implements interfaceAction {

    public void coolDown(Character A,Character B){ //skill5 한정
        if(A.getSkill5Cool()>0){
            A.setSkill5Cool(A.getSkill5Cool()-1);
        }
        if(B.getSkill5Cool()>0){
            B.setSkill5Cool(B.getSkill5Cool()-1);
        }

    }

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
    public int attack2(Character A){
        int damage = 2*((int)(Math.random() * 10) + 1);
        System.out.println("#:"+ damage); //test용
        return damage;

    }

    @Override
    public int attack3(Character A){
        int damage = 3*((int)(Math.random() * 10) + 1);
        System.out.println("#:"+ damage); //test용
        return damage;

    }

    @Override
    public int attackHard(Character A){
        int damage = 5*((int)(Math.random() * 10) + 1);
        System.out.println("#:"+ damage); //test용
        return damage;

    }
}

