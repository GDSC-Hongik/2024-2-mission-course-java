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
                if(isSkill(B,choiceB)==0) return;
                int[] actionList = {action.attack(),action.defense(),action.attack2(),action.attack3(),action.attackHard()};
                int damage = max(0,actionList[choiceB-1]-actionList[1]); //damage는 음수일 수 없음
                A.losehp(damage);
            }
            else{ //B가 방어인 경우
                if(isSkill(A,choiceA)==0) return;
                int[] actionList = {action.attack(),action.defense(),action.attack2(),action.attack3(),action.attackHard()};
                int damage = max(0,actionList[choiceA-1]-actionList[1]);
                B.losehp(damage);
            }
        }
        else{//둘다 공격인 경우
            if(isSkill(A,choiceA)==0) return; //마나 없음
            else{int[] actionListA = {action.attack(),action.defense(),action.attack2(),action.attack3(),action.attackHard()};
            int damageA = actionListA[choiceA-1]; //A가 먼저 공격
            B.losehp(damageA);
            }
            if(B.getHp()<=0) return; // B죽음
            if(isSkill(B,choiceB)==0) return; //마나 없음
            int[] actionListB = {action.attack(),action.defense(),action.attack2(),action.attack3(),action.attackHard()};
            int damageB = actionListB[choiceB-1];
            A.losehp(damageB);
        }
    }


    public void runGame(){
        for(int turn=0; A.alive()&&B.alive()&&turn<end;turn++){
            print(A,B);
            Scanner sc = new Scanner(System.in);
            int choiceA = sc.nextInt();
            print(B,A);
            int choiceB =sc.nextInt();
            inTurn(choiceA,choiceB);
        }

        //for문 끝났을 경우 -> turn 횟수 소진 & 한 명 죽음
        if(A.getHp()<B.getHp()){
            System.out.println(B.getName()+" win");
        } else if (A.getHp()>B.getHp()) {
            System.out.println(A.getName()+" win");
        } else{
            System.out.println("draw");
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

    public int isSkill(Character A, int choice){
        int tempMp = A.getMp();
        if(choice==3){
            if(tempMp<2) {//마나가 없는 경우
                return 0;
            }
            else{
                A.losemp(2);
                return 3; //리스트 인덱스
            }
        }
        else if(choice==4){
            if(tempMp<3) {//마나가 없는 경우
                return 0;
            }
            else{
                A.losemp(3);
                return 4; //리스트 인덱스
            }
        }else if(choice==5){
            if(tempMp<5) {//마나가 없는 경우
                return 0;
            }
            else{
                A.losemp(5);
                return 5; //리스트 인덱스
            }
        }else{ //1 or 2
            return choice;
        }
    }
}
