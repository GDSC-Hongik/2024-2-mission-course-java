package com.gdsc.game.controller;

import com.gdsc.game.model.Game;
import com.gdsc.game.model.Skill;
import com.gdsc.game.model.Character;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameController {

    private Character knight;
    private Character slime;
    private Game game;
    private Map<String,Character> characters = new HashMap<>();

    // 생성자
    // 캐릭터 초기화, 몇 턴동안 진행하는지 초기화
    public GameController() {
        // 스킬 생성
        Skill double_strike = new Skill("2번 베기", 2, 0);
        Skill triple_strike = new Skill("3번 베기", 3, 0);
        Skill power_strike = new Skill("쎄게 때리기", 5, 2);
        Skill fire_arrow = new Skill("불 화살", 3, 0);
        Skill ice_arrow = new Skill("얼음 화살", 3, 1);

        // 캐릭터 생성
        this.knight = new Character("knight", 50, 30, new Skill[]{double_strike, triple_strike, power_strike});
        this.slime = new Character("slime", 30, 20, new Skill[]{fire_arrow, ice_arrow});

        // 캐릭터를 맵에 저장
        characters.put("knight",this.knight);
        characters.put("slime",this.slime);

        // 게임 생성
        this.game = new Game(knight, slime, 5);
    }

    // 게임 상태 조회
    // '현재 턴, 최대 턴, player 2명, 현재 player, 상대 player, 게임 종료 여부' 출력
    @GetMapping("/state")
    public Map<String, Object> getGameState() {
        return game.getGameState();
    }

    // 캐릭터 상태 조회
    // '지정한 캐릭터의 현재 상태, 가능한 행동들' 출력
    @GetMapping("/character/{name}")
    public Character getCharacterState(@PathVariable String name) {
        Character character = characters.get(name.toLowerCase());

        if (character == null) {
            return null;
        }

        // 복사본 생성 및 정렬
        Skill[] sortedSkills = Arrays.copyOf(character.getSkills(), character.getSkills().length);
        Arrays.sort(sortedSkills, Comparator.comparing(Skill::getName));

        // 캐릭터 복사본 생성
        return new Character(
                character.getName(),
                character.getHealth(),
                character.getMana(),
                sortedSkills
        );
    }

    // 턴 진행
    // 공격 / 방어 / 스킬 사용 -> 1턴 진행
    @PostMapping("/next-turn")
    public Map<String,Object> nextTurn(@RequestParam int action, @RequestParam(required = false) Integer skillIndex) {
        return game.nextTurn(action, skillIndex != null ? skillIndex : -1);
    }

    /*
    // 캐릭터 가능한 행동들 조회
    @GetMapping("/character/{name}/actions")
    public String getCharacterActions(@PathVariable String name, @RequestParam(required = false) String sortBy) {
        if ("knight".equalsIgnoreCase(name)) {
            return getActions(knight, sortBy);
        }
        else if ("slime".equalsIgnoreCase(name)) {
            return getActions(slime, sortBy);
        }
        return "Invalid character name";
    }

    private String getActions(Character character, String sortBy) {
        StringBuilder actions = new StringBuilder("Available actions: Attack, Defend, ");
        for (Skill skill : character.getSkills()) {
            actions.append(skill.getName()).append(", ");
        }

        if ("name".equalsIgnoreCase(sortBy)) {
            return actions.toString().replaceFirst(", $", " (sorted by name)");
        }
        else if ("cooldown".equalsIgnoreCase(sortBy)) {
            return actions.toString().replaceFirst(", $", " (sorted by cooldown)");
        }
        return actions.toString().replaceFirst(", $", "");
    }
     */
/*
    // 공격 실행
    @PostMapping("/attack")
    public String attack(@RequestParam String attackerName) {
        if ("knight".equalsIgnoreCase(attackerName)) {
            int damage = knight.attack();
            slime.decreaseHealth(damage);
            return "Knight attacks Slime for " + damage + " damage";
        }
        else if ("slime".equalsIgnoreCase(attackerName)) {
            int damage = slime.attack();
            knight.decreaseHealth(damage);
            return "Slime attacks Knight for " + damage + " damage";
        }
        return "Invalid attacker name";
    }

    // 방어 실행
    @PostMapping("/defend")
    public String defend(@RequestBody String defenderName) {
        int damage;
        if ("knight".equalsIgnoreCase(defenderName)) {
            damage = slime.attack();
            int reducedDamage = knight.defend();
            knight.decreaseHealth(damage - reducedDamage);
            return "Knight defends, reducing damage to " + reducedDamage;
        }
        else if ("slime".equalsIgnoreCase(defenderName)) {
            damage = knight.attack();
            int reducedDamage = slime.defend();
            slime.decreaseHealth(damage - reducedDamage);
            return "Slime defends, reducing damage to " + reducedDamage;
        }
        return "Invalid defender name";
    }

    // 스킬 실행
    @PostMapping("/skill")
    public String useSkill(@RequestParam String userName, @RequestParam int skillIndex) {
        if ("knight".equalsIgnoreCase(userName)) {
            Skill skill = knight.getSkills()[skillIndex];
            int damage = knight.useSkill(skill);
            slime.decreaseHealth(damage);
            return "Knight uses " + skill.getName() + ", dealing " + damage + " damage to Slime";
        }
        else if ("slime".equalsIgnoreCase(userName)) {
            Skill skill = slime.getSkills()[skillIndex];
            int damage = slime.useSkill(skill);
            knight.decreaseHealth(damage);
            return "Slime uses " + skill.getName() + ", dealing " + damage + " damage to Knight";
        }
        return "Invalid user name";
    }
 */

}
