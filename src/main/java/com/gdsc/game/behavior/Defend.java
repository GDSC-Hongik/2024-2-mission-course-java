package com.gdsc.game.behavior;

import com.gdsc.game.character.Character;
import java.util.Random;

public class Defend implements Behavior {
    // Generate random number
    private final Random randomNum = new Random();

    @Override
    public void execute(Character MyChar, Character OpponentChar) {
        int range = randomNum.nextInt(10) + 1;
        MyChar.setDefense(range); // 방어값 설정
        System.out.printf("%s가 방어 태세를 취합니다! (%d 데미지 감소)%n", MyChar.getName(), range);
    }
    @Override
    public String getDescription() {
        return "방어(1 ~ 10)";
    }

}
