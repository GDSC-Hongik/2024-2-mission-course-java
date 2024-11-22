package com.gdsc.game;

import java.util.Scanner;

public class Game {
    private int turnCount;
    private int maxTurns;
    private Character player1;
    private Character player2;
    private Character currentPlayer;
    private Character opponent;

    // 생성자
    public Game(Character player1, Character player2, int maxTurns) {
        this.player1 = player1;
        this.player2 = player2;
        this.maxTurns = maxTurns;
        this.turnCount = 0;
        this.currentPlayer = player1;
        this.opponent = player2;
    }

    // 턴 진행 로직
    public void nextTurn() {
        if(isGameOver()){
            return;
        }

        printStatus();

        // 플레이어 행동 선택
        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();

        int damage = 0;
        switch(action){
            // 공격
            case 1:
                damage = currentPlayer.attack();
                opponent.decreaseHealth(damage);
                break;

            // 방어
            case 2:
                damage = opponent.attack() - currentPlayer.defend();
                if (damage < 0) damage = 0;
                currentPlayer.decreaseHealth(damage);
                break;

            // 스킬 사용
            default:
                Skill[] skills = currentPlayer.getSkills();
                int skill = action - 3;
                damage = currentPlayer.useSkill(skills[skill]);
                if (damage > 0){
                    opponent.decreaseHealth(damage);
                }
                else{
                    System.out.println("스킬 사용 불가");
                }
                break;
        }
        reduceCooldowns();
        swapPlayers();
        turnCount++;
    }

    // 쿨다운 적용
    private void reduceCooldowns(){
        for(Skill skill : currentPlayer.getSkills()){
            skill.reduceCooldown();
        }
    }

    // 플레이어 교체
    private void swapPlayers(){
        Character temp = currentPlayer;
        currentPlayer = opponent;
        opponent = temp;
    }

    // 상태 출력
    private void printStatus() {
        System.out.println(currentPlayer.getName() + " 체력: " + currentPlayer.getHealth() + " 마나: " + currentPlayer.getMana()
                + " | " + opponent.getName() + " 체력: " + opponent.getHealth() + " 마나: " + opponent.getMana());
        System.out.println("1. 공격(1 ~ 10) ");
        System.out.println("2. 방어(1 ~ 10) ");

        int skillNumber = 3;
        for (Skill skill : currentPlayer.getSkills()) {
            System.out.println(skillNumber + ". " + skill.getName() + "(" + skill.getManaCost()+"~"+skill.getManaCost()*10+")"+" - " + skill.getManaCost() + "MP" + " - " + skill.getCooldown() + "턴");
            skillNumber++;
        }
    }


    // 게임 종료 여부 확인
    boolean isGameOver() {
        if(player1.getHealth() <= 0 || player2.getHealth() <= 0){
            System.out.println("게임 종료");
            if(player1.getHealth() > player2.getHealth()){
                System.out.println(player1.getName() + "이(가) 이겼습니다!");
            }
            else{
                System.out.println(player2.getName() + "이(가) 이겼습니다!");
            }
            return true;
        }

        if (turnCount >= maxTurns) {
            System.out.println("최대 턴 수 도달, 게임 종료");
            if(player1.getHealth() > player2.getHealth()){
                System.out.println(player1.getName() + "이(가) 이겼습니다!");
            }
            else{
                System.out.println(player2.getName() + "이(가) 이겼습니다!");
            }
            return true;
        }

        return false;
    }

}
