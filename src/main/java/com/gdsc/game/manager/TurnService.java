package com.gdsc.game.manager;

import com.gdsc.game.action.Action;
import com.gdsc.game.action.ActionService;
import com.gdsc.game.action.skill.Skill;
import com.gdsc.game.player.Player;
import com.gdsc.game.player.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class TurnService {

    private final PlayerService playerService;
    private final ActionService actionService;

    private int currentTurn = 1;
    private final int MAX_TURNS = 5;
    // 두 플레이어의 현재 턴 공격 데미지 또는 방어 데미지를 저장하기 위한 Map : 공격은 양수, 방어는 음수
    private Map<Player, Integer> damageMap = new HashMap<>();

    public void nextTurn() {
        currentTurn++;
    }
    public boolean isFinished() {
        return currentTurn >= MAX_TURNS;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }
    public int getMaxTurns() {
        return MAX_TURNS;
    }

    // 플레이어에게 행동을 입력받음
    public String startTurn(String playerName, int actionIdx) {
        List<Player> players = playerService.getPlayers();
        Player player = new Player();
        Player opponent = new Player();


        for (Player p : players) {  // 플레이어 데이터에서 나와 상대 불러오기
            if (p.getName().equals(playerName)) {player = p;}
            else {opponent = p;}
        }
        if (!isFinished() && player.getHp() > 0 && opponent.getHp() > 0) {
            if (player.getTurn() == currentTurn) {
                playTurn(player, opponent, actionIdx);
            } else {
                throw new RuntimeException("상대의 턴 완료를 기다리십시오!");
            }
        }

        // 게임 종료 메시지
        if (player.getHp() <= 0 && opponent.getHp() <= 0) {
            return "두 플레이어가 모두 쓰려졌습니다....무승부!";
        } else if (player.getHp() <= 0) {
            return opponent.getName() + "이(가) 이겼습니다!";
        } else if (opponent.getHp() <= 0) {
            return player.getName() + "이(가) 이겼습니다!";
        } else {
            return "최대 턴 도달!! 게임 종료!";
        }
    }


    private void playTurn(Player player, Player opponent, int actionIdx) {

        // 플레이어의 행동 선택
        Action action = chooseAction(player, actionIdx);
        int damage = action.execute(player, opponent);
        damageMap.put(player, damage);
        // 결과 처리
        if (opponent.getTurn() == currentTurn) {
            processResults(player, damageMap.get(player), opponent, damageMap.get(opponent));
            player.updateCooldowns(); // 본인 스킬 쿨타임 갱신하기
            opponent.updateCooldowns(); // 상대 스킬 쿨타임 갱신하기
            nextTurn();
        }
    }

    private Action chooseAction(Player player, int actionIdx) throws RuntimeException {
        Action chosenAction = actionService.getAction(actionIdx);
        if (chosenAction instanceof Skill) {    // 스킬의 경우
            Skill skill = (Skill) chosenAction;
            int requiredMp = skill.getMpCost();  // 스킬의 소비 MP
            if (!player.canUseSkill(skill.getName())) {
                throw new RuntimeException("쿨타임이 남았습니다. 다시 선택하세요.");
            }
            else if (player.getMp() < requiredMp) {
                throw new RuntimeException("MP가 부족합니다. 다시 선택하세요.");
            }
            else {
                player.applySkillCooldown(skill);
                return chosenAction;
            }
        } else {
            return chosenAction; // 스킬이 아닌 일반 액션
        }
    }

    public void processResults(Player player1, int damage1, Player player2, int damage2) {
        // 플레이어 1과 2의 선택을 각각 저장
        boolean player1Attacking = damage1 >= 0;
        boolean player2Attacking = damage2 >= 0;

        int damageToPlayer1; // 플레이어2가 플레이어1에게 주는 데미지
        int damageToPlayer2; // 플레이어1이 플레이어2에게 주는 데미지


        if (player1Attacking) {
            if (player2Attacking) { // 둘 다 공격
                damageToPlayer1 = damage2;
                damageToPlayer2 = damage1;
            }
            else {  // 1 공격, 2 방어
                damageToPlayer1 = 0;
                damageToPlayer2 = Math.max(0, damage1 + damage2); // 방어구 + 데미지
            }
        }
        else {
            if (player2Attacking) { // 1 방어, 2 공격
                damageToPlayer1 = Math.max(0, damage2 + damage1);
                damageToPlayer2 = 0;
            }
            else { // 1 방어, 2 방어
                damageToPlayer1 = 0;
                damageToPlayer2 = 0;
            }
        }
     // 데미지 적용
        player1.applyDamage(damageToPlayer1);
        player2.applyDamage(damageToPlayer2);

        currentTurn++; // 턴 종료
    }
}
