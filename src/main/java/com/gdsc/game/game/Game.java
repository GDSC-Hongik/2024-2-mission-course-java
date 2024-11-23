package com.gdsc.game.game;

import com.gdsc.game.character.CharacterAction;
import com.gdsc.game.character.GameCharacter;

import java.util.Scanner;

public class Game {
    private final CharacterAction player1;
    private final CharacterAction player2;
    private final int turns;
    private final Scanner scanner;

    public Game(String player1Name, String player2Name, int turns) {
        this.player1 = new GameCharacter(player1Name, 50, 30);
        this.player2 = new GameCharacter(player2Name, 30, 20);
        this.turns = turns;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int currentTurn = 0;
        while (currentTurn < turns) {
            if (!processTurn(player1, player2)) break;
            if (!processTurn(player2, player1)) break;
            currentTurn++;
        }
        System.out.println("Game over!");
    }

    private boolean processTurn(CharacterAction attacker, CharacterAction defender) {
        System.out.println(attacker.getStatus(defender));

        int choice = Integer.parseInt(scanner.nextLine());
        int damage = 0;

        switch (choice) {
            case 1 -> damage = attacker.attack();  // 어택을 선택한 경우
            case 2 -> attacker.defend();  // 방어를 선택한 경우
            case 3, 4, 5 -> damage = attacker.useSkill(choice - 3);  // 스킬을 선택한 경우
        }

        // 스킬을 사용한 경우만 마나 차감
        if (damage > 0) {
            if (choice >= 3 && choice <= 5) {
                attacker.useSkill(choice - 3); // 스킬 사용 시 마나 차감
            }
            defender.takeDamage(damage);
        }

        attacker.updateSkills();

        if (defender.getHp() <= 0) {
            System.out.println(attacker.getName() + "is win.");
            return false;
        }
        return true;
    }
}
