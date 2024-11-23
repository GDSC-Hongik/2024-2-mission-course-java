package com.gdsc.game.behavior;

import com.gdsc.game.character.Character;
import java.util.Random;

public class Attack implements Behavior{
    // Generate random number
    private final Random randomNum = new Random();

    @Override
    public void execute(Character myChar, Character opponentChar) {
        int damage = randomNum.nextInt(10) + 1;
        opponentChar.setHp(opponentChar.getHp() - damage);
        System.out.printf("%s가 %s를 공격하여 %d의 데미지를 입혔습니다.%n", myChar.getName(), opponentChar.getName(), damage);
    }
}