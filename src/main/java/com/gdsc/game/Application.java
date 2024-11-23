package com.gdsc.game;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /// TODO : 잘못된 입력 처리

        // 게임 시작
        System.out.println("===============GAME START=================");
        System.out.println("캐릭터 2명의 이름을 입력해주세요 (쉼표로 구분해주세요):");
        String names[] = scanner.nextLine().split(",");
        System.out.println("턴 수을 입력해주세요:");
        int turns = scanner.nextInt();

        Character character1, character2;

        if (names[0].equals("knight")) {
            character1 = new Character(names[0], 50, 30);
            character2 = new Character(names[1], 10, 5);
        } else {
            character1 = new Character(names[1], 50, 30);
            character2 = new Character(names[0], 10, 5);
        }

        Character current = character1;
        Character target = character2;

        System.out.print(character1.getName() + " 체력: " + character1.getHealthPoint() + " 마나: " + character1.getManaPoint() + " | ");
        System.out.println(character2.getName() + " 체력: " + character2.getHealthPoint() + " 마나 " + character2.getManaPoint());


        while (turns > 0) {
            System.out.println("\n" + current.getName() + "의 차례입니다.");
            System.out.println("1. 공격(1 ~ 10)");
            System.out.println("2. 방어(1 ~ 10)");
            System.out.println("3. 두번베기(2 ~ 20) - 2MP -0턴");
            System.out.println("4. 3번베기(3 ~ 30) - 3MP - 0턴");
            System.out.println("5. 쎼게 때리기(5 ~ 50) - 5MP - 2턴");

            System.out.print("행동을 선택하세요: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    AttackAction attack = new AttackAction();
                    attack.execute(current, target);
                    break;

                case 2:
                    DefenseAction defense = new DefenseAction();
                    defense.execute(current, target);
                    break;

                case 3:
                    if (current.getManaPoint() >= 2) {
                        SkillAction skill = new CutTwice();
                        skill.execute(current, target);
                    } else {
                        System.out.println("마나부족");
                    }
                    break;

                case 4:
                    if (current.getManaPoint() >= 3) {
                        SkillAction skill = new CutThreeTimes();
                        skill.execute(current, target);
                    } else {
                        System.out.println("마나부족");
                    }
                    break;

                default:
                    if (current.getManaPoint() >= 5) {
                        SkillAction skill = new PowerStrike();
                        skill.execute(current, target);
                    } else {
                        System.out.println("마나부족");
                    }
                    break;

            }
            Character temp = current;
            current = target;
            target = temp;

            if (current == character1) {
                turns--;
            }

            System.out.print(character1.getName() + " 체력: " + character1.getHealthPoint() + " 마나: " + character1.getManaPoint() + " | ");
            System.out.println(character2.getName() + " 체력: " + character2.getHealthPoint() + " 마나 " + character2.getManaPoint());

            if (!character1.isAlive()) {
                System.out.println(character2.getName() + "가 이겼습니다!");
            } else {
                System.out.println(character1.getName() + "가 이겼습니다!");

            }

        }

    }
}
