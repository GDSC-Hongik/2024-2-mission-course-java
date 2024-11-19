package com.gdsc.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

import static java.lang.Math.max;


interface Turn{
    void inTurn(int choiceA, int choiceB); //턴 내부 상황

}

public class game implements Turn{
    private int turn; //현재 turn
    private int end; // 총 turn 횟수

    private Character A;
    private Character B;

    Action action = new Action();

    private int[] actionList = {action.attack(),action.defense(),action.attack2(),action.attack3(),action.attackHard()};



    public game(Character A, Character B, int turn){ //매개변수 turn은 총 횟수
        this.A = A;
        this.B = B;
        this.end = turn;
    }

    @Override
    public void inTurn(int choiceA, int choiceB){
        Action a = new Action();
        Action b = new Action();

        if(choiceA==2||choiceB==2){
            if(choiceA==2&&choiceB==2) {return;} //둘다 방어일 경우 turn 넘김
            else if (choiceA==2){ //A가 방어인 경우
                int damage = max(0,actionList[choiceB]-actionList[1]); //damage는 음수일 수 없음
                A.losehp(damage);
            }
            else{ //B가 방어인 경우
                int damage = max(0,actionList[choiceA]-actionList[1]);
                B.losehp(damage);
            }
        }
        else{//둘다 공격인 경우
            int damageA = actionList[choiceA]; //A가 먼저 공격
            B.losehp(damageA);
            if(B.getHp()<=0) return; // B죽음
            int damageB = actionList[choiceB];
            A.losehp(damageB);
        }
    }


    public void runGame(){
        for(int turn=0; A.alive()&&B.alive()&&turn<end;turn++){
            print(A,B);
            Scanner sc1 = new Scanner(System.in);
            int choiceA = sc1.nextInt();
            print(B,A);
            Scanner sc2 = new Scanner(System.in);
            int choiceB =sc2.nextInt();
            inTurn(choiceA,choiceB);

        }
    }

    public void print(Character A, Character B){
        System.out.println(A.getName()+" hp:"+A.getHp()+" mp:"+A.getMp()+" | "+B.getName()+" hp:"+B.getHp()+" mp:"+B.getMp());
        System.out.println("1. attack(1~10)");
        System.out.println("2. defense(1~10)");
        System.out.println("3. double attack(2~20) - 2MP - 0turn");
        System.out.println("4. triple attack(3~30) - 3MP - 0turn");
        System.out.println("5. hard attack(5~50) - 5MP - 2turns");
    }
}
