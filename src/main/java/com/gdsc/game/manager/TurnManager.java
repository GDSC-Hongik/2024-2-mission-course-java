package com.gdsc.game.manager;

import com.gdsc.game.action.Action;
import com.gdsc.game.action.basic.BasicAction;
import com.gdsc.game.action.skill.Skill;
import com.gdsc.game.player.Player;

import java.util.List;
import java.util.Scanner;

public class TurnManager {
    private Scanner scanner;

    public TurnManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startGame(Player player1, Player player2, int maxTurns) {
        int turn = 1;

        while (turn <= maxTurns && player1.getHp() > 0 && player2.getHp() > 0) {
            System.out.println("==== 턴 " + turn + " ====");

            try{
                playTurn(player1, player2);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            turn++;
        }

        // 게임 종료 메시지
        if (player1.getHp() <= 0 && player2.getHp() <= 0) {
            System.out.println("두 플레이어가 모두 쓰려졌습니다....무승부!");
        } else if (player1.getHp() <= 0) {
            System.out.println(player2.getName() + "이(가) 이겼습니다!");
        } else if (player2.getHp() <= 0) {
            System.out.println(player1.getName() + "이(가) 이겼습니다!");
        } else {
            System.out.println("최대 턴 도달!! 게임 종료!");
            printPlayerState(player1, player2);
        }
    }
    private void playTurn(Player player1, Player player2) throws Exception {

        printPlayerState(player1, player2);
        printActions(player1);

        // 플레이어1 행동 선택
        Action action1 = chooseAction(player1);
        // 플레이어2 행동 선택
        Action action2 = chooseAction(player2);
        if (action1 == null || action2 == null) {
            throw new Exception("action1 or action2 is null!");
        }

        // 두 플레이어의 행동 실행
        ActionResult result1 = action1.execute(player1, player2);
        ActionResult result2 = action2.execute(player2, player1);

        // 결과 처리
        processResults(player1, result1, player2, result2);
        System.out.println();

        // 스킬 쿨타임 하나씩 줄기
        player1.updateCooldowns();
        player2.updateCooldowns();
    }

    private Action chooseAction(Player player) {
        int choice = -1;
        List<Action> actions = player.getActions();
        int maxChoices = actions.size();
        while (true) {
            System.out.println(player.getName() + ", 행동을 선택하세요(주어진 숫자 내에서 입력): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= maxChoices) {
                    Action chosenAction = actions.get(choice - 1); // index 수정
                    if (chosenAction instanceof Skill) {
                        Skill skill = (Skill) chosenAction;
                        int requiredMp = skill.getMpCost();  // 스킬의 소비 MP
                        if (!player.canUseSkill(skill.getName())) {
                            System.out.println("쿨타임이 남았습니다. 다시 선택하세요.");
                        }
                        else if (player.getMp() < requiredMp) {
                            System.out.println("MP가 부족합니다. 다시 선택하세요.");
                        }
                        else {
                            player.applySkillCooldown(skill);
                            return chosenAction;
                        }
                    } else {
                        return chosenAction; // 스킬이 아닌 일반 액션
                    }
                } else {
                    System.out.println("잘못된 번호입니다. 다시 선택하세요.");
                }
            } else {
                System.out.println("숫자를 입력하세요.");
                scanner.next(); // 잘못된 입력을 소비한 후 루프 처음부터 실행
            }
        }
    }

    private void processResults(Player player1, ActionResult result1, Player player2, ActionResult result2) {

        // 플레이어 1과 2의 선택을 각각 저장
        boolean player1Attacking = result1.getDamage() >= 0;
        boolean player2Attacking = result2.getDamage() >= 0;

        int damageToPlayer1; // 플레이어2가 플레이어1에게 주는 데미지
        int damageToPlayer2; // 플레이어1이 플레이어2에게 주는 데미지


        if (player1Attacking) {
            if (player2Attacking) { // 둘 다 공격
                damageToPlayer1 = result2.getDamage();
                damageToPlayer2 = result1.getDamage();
            }
            else {  // 1 공격, 2 방어
                damageToPlayer1 = 0;
                damageToPlayer2 = Math.max(0, result1.getDamage() + result2.getDamage()); // 방어구 + 데미지
            }
        }
        else {
            if (player2Attacking) { // 1 방어, 2 공격
                damageToPlayer1 = Math.max(0, result2.getDamage() + result1.getDamage());
                damageToPlayer2 = 0;
            }
            else { // 1 방어, 2 방어
                damageToPlayer1 = 0;
                damageToPlayer2 = 0;
            }
        }

        // 데미지 적용
        player1.applyDamage(damageToPlayer1);
        player2.applyDamage(damageToPlayer2);
        // 결과 메시지 출력
        System.out.println(player1.getName()  + ": " + result1.getMessage());
        System.out.println(player2.getName()  + ": " + result2.getMessage());
    }
    public void printPlayerState(Player player1, Player player2) {
        String player1State = player1.getName() + " 체력: " + player1.getHp() + ", 마나: " + player1.getMp();
        String player2State = player2.getName() + " 체력: " + player2.getHp() + ", 마나: " + player2.getMp();
        System.out.println(player1State + " | " + player2State);
    }
    private void printActions(Player player) {
        int i = 1;
        for (Action action : player.getActions()) {
            if (action instanceof BasicAction) {
                BasicAction basicAction = (BasicAction) action;
                System.out.println(i + ". " + basicAction.getName() + "(" + basicAction.getMinDamage()
                        + " ~ " + basicAction.getMaxDamage() + ")");
            }
            else if (action instanceof Skill) {
                Skill skill = (Skill) action;
                System.out.println(i + ". " + skill.getName() + "(" + skill.getMpCost()*skill.getMinDamage()
                        + " ~ " + skill.getMpCost()*skill.getMaxDamage() + ")" + " - " + skill.getMpCost() + "MP - "
                        + player.getSkillCooldowns().get(skill.getName()) + "턴");
            }
            i++;
        }
    }
}
