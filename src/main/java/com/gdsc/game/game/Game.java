package com.gdsc.game.game;

import com.gdsc.game.character.Character;

import java.util.Scanner;

public class Game {
    static Scanner sc = new Scanner(System.in);

    // method
    public static void main(String[] args) {
        System.out.println("2명의 캐릭터 이름을 쉼표(,) 를 통해 구분하여 입력하세요.");

        // name array
        String[] names = sc.nextLine().split(",");

        // hp와 mp는 예시와 동일하게 생성
        Character character1 = new Character(names[0].trim(), 50, 30);
        Character character2 = new Character(names[1].trim(), 10, 5);

        // 행동
        // ......

        System.out.println("몇 턴 동안 진행하시겠습니까?");
        int inputTurns = sc.nextInt();

        // 입력받은 턴 수만큼 게임을 진행
        for (int turn = 1; turn <= inputTurns; turn++) {
            // 체력이 0 이하이면 게임을 종료
            if (!character1.isAlive() || !character2.isAlive()) { break; }

            System.out.printf("%s 체력: %d 마나: %d | %s 체력: %d 마나: %d%n",
                    character1.getName(), character1.getHp(), character1.getMp(),
                    character2.getName(), character2.getHp(), character2.getMp());

            // 추가............
        }

        // 결과 출력
        if (character1.isAlive()) {
            System.out.printf("%s가 이겼습니다!%n", character1.getName());
        } else {
            System.out.printf("%s가 이겼습니다!%n", character2.getName());
        }
    }
}
