package com.gdsc.game.service;

import com.gdsc.game.domain.*;
import com.gdsc.game.domain.Character;
import com.gdsc.game.dto.ActionResponse;
import com.gdsc.game.dto.CharacterStatusResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class GameService {
    private final Scanner scanner;
    private Character character1;
    private Character character2;
    private int turns;

    public GameService() {
        scanner = new Scanner(System.in);
        this.character1 = new Character("knight", 50, 30);
        this.character2 = new Character("slime", 10, 5);
        this.turns = 5;
    }

    public CharacterStatusResponse getStatus(String name) {
        Character character = findCharacterByName(name);
        return new CharacterStatusResponse(
                character.getName(),
                character.getHealthPoint(),
                character.getManaPoint()
        );
    }

    private Character findCharacterByName(String name) {
        if (character1.getName().equals(name)) return character1;
        if (character2.getName().equals(name)) return character2;
        throw new IllegalArgumentException("캐릭터를 찾지 못하였습니다");
    }

    public List<ActionResponse> showActions(String characterName, String actionName) {
        Character character = findCharacterByName(characterName);
        List<ActionResponse> actions = new ArrayList<>();

        actions.add(new ActionResponse("공격", "데미지(1-10)", 0, 0, turns));
        actions.add(new ActionResponse("방어", "방어(1-10)", 0, 0, turns));
        actions.add(new ActionResponse("두번베기", "데미지(2-20)", 2, 0, turns));
        actions.add(new ActionResponse("3번베기", "데미지(3-30)", 3, 0, turns));
        actions.add(new ActionResponse("쎄게때리기", "데미지(5-50)", 5, 2, turns));

        if (actionName.equals("name")) {
            actions.sort((a1, a2) -> a1.getActionName().compareTo(a2.getActionName()));
        } else if (actionName.equals("cooltime")) {
            actions.sort((a1, a2) -> Integer.compare(a1.getRemainingCooltime(), a2.getRemainingCooltime()));
        }

        return actions;
    }

    public void startGame() {
        System.out.println("===============GAME START=================");
        System.out.println("캐릭터 2명의 이름을 입력해주세요 (쉼표로 구분해주세요):");
        String names[] = scanner.nextLine().split(",");
        System.out.println("턴 수을 입력해주세요:");
        this.turns = scanner.nextInt();

        if (!names[0].equals("knight")) {
            Character temp = character1;
            character1 = character2;
            character2 = temp;
        }

        Character current = character1;
        Character target = character2;


        while (turns > 0) {
            System.out.println("\n" + current.getName() + "의 차례입니다.");
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

            if (turns <= 0 || !character1.isAlive() || !character2.isAlive()) {
                if (!character1.isAlive()) {
                    System.out.println(character2.getName() + "가 이겼습니다!");
                } else if (!character2.isAlive()) {
                    System.out.println(character1.getName() + "가 이겼습니다!");
                }
                break;
            }
        }
    }
}


