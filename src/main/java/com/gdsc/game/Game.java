package com.gdsc.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

import static java.lang.Math.max;

public class Game  {
    private int turn; //현재 turn
    private int end; // 총 turn 횟수

    private Character A;
    private Character B;

    Action action = new Action();


    public Game(Character A, Character B, int turn) { //매개변수 turn은 총 횟수
        this.A = A;
        this.B = B;
        this.end = turn;
    }

    public void act(Character A,Character B, int choice){ //행위자는 A (A가 공격 or A shield 생성)
        A.setShield(0); //shield를 초기화 (이미 썼거나 처음인 경우)
        int[] actionList = {action.attack(), action.defense(), action.attack2(A), action.attack3(A), action.attackHard(A)}; //인덱스 0~4
        if (choice ==2){
            int shield = actionList[choice-1];
            A.setShield(shield);
        } else { //choice == 1 or 3 or 4 or 5
            if(A.getMp()<mana(choice) ){ //마나없는 경우
                System.out.println("no have mp!");
                return;
            }
            if(choice==5 && A.getSkill5Cool()>0){ //쿨타임이 존재하는 스킬일 경우(인덱스=4)
                System.out.println("skill is cool");
                return;
            }
            int opponentShield = B.getShield();
            if(choice==5){
                A.setSkill5Cool(3); //턴 끝날때 --을 해주기에 결과적으로 2번의 쿨타임을 가짐
            }
            A.losemp(mana(choice));
            int damage = actionList[choice-1];
            B.losehp(max(0,damage- opponentShield)); //음수를 데미지 입힐 수 없음
        }

    }


    public void runGame() {
        for (turn = 0; A.alive() && B.alive() && turn < end; turn++) {
            System.out.println("turn:"+ turn);
//            Scanner sc = new Scanner(System.in);
            //A먼저 진행
            print(A,B);
//            int choiceA = sc.nextInt();
//            act(A,B,choiceA);

            if(!B.alive()){ //A공격으로 B 사망
                break;
            }

            print(B,A);
//            int choiceB = sc.nextInt();
//            act(B,A,choiceB);
            action.coolDown(A,B);
        }

        //for문 끝났을 경우 -> turn 횟수 소진 & 한 명 죽음
        if (A.getHp() < B.getHp()) {
            System.out.println(B.getName() + " win");
        } else if (A.getHp() > B.getHp()) {
            System.out.println(A.getName() + " win");
        } else {
            System.out.println("draw");
        }


    }

    //공격 마나
    public int mana(int choice){
        if(choice==1){
            return 0;
        }
        else if(choice==3){
            return 2;
        }
        else if(choice==4){
            return 3;
        }
        else{ //choice==5
            return 5;
        }
    }

    public void print(Character A, Character B) {
        System.out.println(A.getName() + " hp:" + A.getHp() + " mp:" + A.getMp() + " | " + B.getName() + " hp:" + B.getHp() + " mp:" + B.getMp());
        System.out.println("1. attack(1~10)");
        System.out.println("2. defense(1~10)");
        System.out.println("3. double attack(2~20) - 2MP - 0turn");
        System.out.println("4. triple attack(3~30) - 3MP - 0turn");
        System.out.println("5. hard attack(5~50) - 5MP - 2turns");
    }

}

