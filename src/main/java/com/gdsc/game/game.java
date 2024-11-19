package com.gdsc.game;

import java.util.Scanner;

interface Turn{
    void inTurn(int choiceA, int choiceB); //턴 내부 상황
    int turnUp(); //턴 증가

}

public class game implements Turn{
    private int turn; //현재 turn
    private int end; // 총 turn 횟수

    private Character A;
    private Character B;

    public game(Character A, Character B, int turn){ //매개변수 turn은 총 횟수
        this.A = A;
        this.B = B;
        this.end = turn;
    }

    @Override
    public void inTurn(int choiceA, int choiceB){

    }

    @Override
    public int turnUp(){
        if (turn==end){ //turn 횟수 소진
            return 0;
        }
        return 1; //계속 게임 진행
    }

    public void runGame(){
        turn = 0;
        for(int i = 0; A.alive()&&B.alive()&&turn<end;i++){
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
