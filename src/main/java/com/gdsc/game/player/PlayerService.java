package com.gdsc.game.player;

import com.gdsc.game.action.skill.Skill;
import com.gdsc.game.manager.TurnService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TurnService turnService;

    public PlayerService(@Lazy PlayerRepository playerRepository, @Lazy TurnService turnService) {
        this.playerRepository = playerRepository;
        this.turnService = turnService;
    }
    @PostConstruct
    public void createPlayerSet() {
        String[] playerNames = {"a", "b"}; // name 세팅

        Skill doubleSlice = new Skill("두 번 베기", 2, 2);
        Skill tripleSlice = new Skill("세 번 베기", 3, 2);
        Skill CriticalHit = new Skill("세게 때리기", 5, 3);
        List<Skill> skills = new ArrayList<>(List.of(doubleSlice, tripleSlice, CriticalHit));

        Player player1 = new Player(playerNames[0]);
        Player player2 = new Player(playerNames[1]);

        player1.setSkillCooldowns(skills);
        player2.setSkillCooldowns(skills);

        registerPlayer(player1);
        registerPlayer(player2);
    }

    private void registerPlayer(Player player) {
        playerRepository.save(player);
    }

    public String printPlayerState() {
        List<Player> players = playerRepository.findAll();
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        String player1State = player1.getName() + " 체력: " + player1.getHp() + ", 마나: " + player1.getMp()
                + "\n남은 쿨타임: " + player1.getSkillCooldowns() + " 남은 턴수: " +  (turnService.getMaxTurns() - player1.getTurn() + 1);
        String player2State = player2.getName() + " 체력: " + player2.getHp() + ", 마나: " + player2.getMp()
                + "\n남은 쿨타임: " + player2.getSkillCooldowns() + " 남은 턴수: " +  (turnService.getMaxTurns() - player2.getTurn() + 1);
        return player1State + "\n\n" + player2State;
    }
    public String printPlayerState(String playerName) {
        Player player = playerRepository.findOneByName(playerName);
        return player.getName() + " 체력: " + player.getHp() + ", 마나: " + player.getMp()
                + "\n\n남은 쿨타임: " + player.getSkillCooldowns() + " 은 턴수: " +  (turnService.getMaxTurns() - player.getTurn() + 1);
    }

    public List<Player> getPlayers () {
        return playerRepository.findAll();
    }
    public Player getPlayer (String playerName) {
        return playerRepository.findOneByName(playerName);
    }
}
