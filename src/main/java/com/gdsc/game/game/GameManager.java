package com.gdsc.game.game;

import com.gdsc.game.character.GameCharacter;
import com.gdsc.game.model.GameStatus;
import com.gdsc.game.model.TurnStatus;

import java.util.HashMap;
import java.util.Map;

public class GameManager {
    private Map<String, GameCharacter> characters = new HashMap<>();
    private TurnManager turnManager;

    // 게임 초기화 메서드
    public GameStatus initialize(String[] players, int turns) {
        // 플레이어와 캐릭터 초기화
        characters.put(players[0], new GameCharacter(players[0], 50, 30));
        characters.put(players[1], new GameCharacter(players[1], 30, 20));

        // 턴 매니저 초기화
        turnManager = new TurnManager(players[0], players[1], turns);

        // 초기 상태 반환
        return getCurrentStatus();
    }

    // 액션을 수행하는 메서드
    public GameStatus performAction(String attackerName, String defenderName, String actionType, Integer skillIndex) {
        GameCharacter attacker = characters.get(attackerName);
        GameCharacter defender = characters.get(defenderName);

        // 액션 처리
        switch (actionType) {
            case "Attack":
                defender.takeDamage(attacker.attack());
                break;
            case "Defend":
                attacker.defend();
                break;
            case "Skill":
                if (skillIndex != null) {
                    defender.takeDamage(attacker.useSkill(skillIndex));
                }
                break;
        }

        // 스킬 업데이트
        attacker.updateSkills();

        // 게임 오버 처리
        if (defender.getHp() <= 0) {
            turnManager.setGameOver(attacker.getName());  // 승자 설정
        } else {
            turnManager.advanceTurn();  // 턴 진행
        }

        // 현재 상태 반환
        return getCurrentStatus();
    }

    // 현재 게임 상태 반환
    public GameStatus getCurrentStatus() {
        TurnStatus turnStatus = turnManager.getTurnStatus();
        return new GameStatus(
                characters.keySet().toArray(new String[0]),  // 플레이어 목록
                turnManager.getTotalTurns(),  // 총 턴 수
                turnStatus.getActivePlayer(),  // 현재 활성 플레이어
                turnManager.getCurrentTurn(),  // 현재 턴
                turnStatus.isGameOver()  // 게임 종료 여부
        );
    }

    // 캐릭터 이름으로 게임 캐릭터 가져오기
    public GameCharacter getCharacter(String name) {
        return characters.get(name);
    }
}
