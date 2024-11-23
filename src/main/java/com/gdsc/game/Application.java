package com.gdsc.game;

import com.gdsc.game.action.Action;
import com.gdsc.game.action.basic.Attack;
import com.gdsc.game.action.basic.Defense;
import com.gdsc.game.action.skill.Skill;
import com.gdsc.game.manager.TurnManager;
import com.gdsc.game.player.Player;

import java.util.*;

public class Application {
    private static Scanner scanner;
    private static final int NUM_OF_ACTIONS = 5;
    private static final int NUM_OF_SKILLS = 3;

    public Application() {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Application app = new Application();
        String[] playerNames = app.inputTwoPlayerName();
        int maxTurns = app.inputNumOfTurns();


        Attack attack = new Attack("공격");
        Defense defense = new Defense("방어");
        Skill doubleSlice = new Skill("두 번 베기", 2, 2);
        Skill tripleSlice = new Skill("세 번 베기", 3, 2);
        Skill CriticalHit = new Skill("세게 때리기", 5, 3);

        List<Action> actions = new ArrayList<>(List.of(attack, defense, doubleSlice, tripleSlice, CriticalHit));
        List<Skill> skills = new ArrayList<>(List.of(doubleSlice, tripleSlice, CriticalHit));

        Player player1 = new Player(playerNames[0], actions, skills);
        player1.getSkillCooldowns().put(doubleSlice.getName(), 0);
        player1.getSkillCooldowns().put(tripleSlice.getName(), 0);
        player1.getSkillCooldowns().put(CriticalHit.getName(), 0);

        Player player2 = new Player(playerNames[1], actions, skills);
        player2.getSkillCooldowns().put(doubleSlice.getName(), 0);
        player2.getSkillCooldowns().put(tripleSlice.getName(), 0);
        player2.getSkillCooldowns().put(CriticalHit.getName(), 0);

        TurnManager turnManager = new TurnManager(scanner);
        // 턴제 게임 시작
        turnManager.startGame(player1, player2, maxTurns);

        scanner.close();

    }

    public String[] inputTwoPlayerName() {
        String input;
        String[] names;

        while (true) {
            System.out.println("2명의 플레이어명을 입력하세요.(e.g. dog,cat)(종료 : 'exit')");
            input = scanner.nextLine();
            if (input.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            }
            names = input.split(",");
            if (names.length == 2) {
                return new String[]{names[0].trim(), names[1].trim()};
            } else {
                System.out.println("제발 '양식'에 맞추어 '2명'의 플레이어명을 입력하세요...");
            }
        }
    }
    public int inputNumOfTurns() {
        while (true) {
            System.out.println("진행하고 싶은 턴 수를 입력하세요.(e.g. 5)(종료 : 'exit')");

            String input = scanner.nextLine();
            if (input.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            }
            if (input.matches("\\d+")) {
                int number = Integer.parseInt(input);
                System.out.println("턴 수: " + number);
                return number;
            }
            else {
                System.out.println("잘못된 양식입니다. 다시 입력하세요.");
            }
        }
    }
}
