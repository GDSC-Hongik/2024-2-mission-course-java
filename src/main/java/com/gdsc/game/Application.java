package com.gdsc.game;

import com.gdsc.game.model.Character;
import com.gdsc.game.model.Game;
import com.gdsc.game.model.Skill;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

@SpringBootApplication
public class Application {
    /*
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));

        // 스킬 생성
        Skill double_strike = new Skill("2번 베기", 2, 0);
        Skill triple_strike = new Skill("3번 베기", 3, 0);
        Skill power_strike = new Skill("쎄게 때리기", 5, 2);
        Skill fire_arrow = new Skill("불 화살", 3, 0);
        Skill ice_arrow = new Skill("얼음 화살", 3, 1);

        // 캐릭터 생성
        Character knight = new Character("knight", 50, 30, new Skill[]{double_strike, triple_strike, power_strike});
        Character slime = new Character("slime", 30, 20, new Skill[]{fire_arrow, ice_arrow});

        // 게임 생성
        Game game = new Game(knight, slime, 5);

        // 게임 진행
        while (!game.isGameOver()) {
            game.nextTurn();
        }
    }
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}