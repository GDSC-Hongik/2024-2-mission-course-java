package com.gdsc.game.action;

import com.gdsc.game.action.basic.Attack;
import com.gdsc.game.action.basic.BasicAction;
import com.gdsc.game.action.basic.Defense;
import com.gdsc.game.action.skill.Skill;
import com.gdsc.game.player.Player;
import com.gdsc.game.player.PlayerService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActionService {

    private final PlayerService playerService;
    private final ActionRepository actionRepository;

    public ActionService(@Lazy PlayerService playerService, @Lazy ActionRepository actionRepository) {
        this.playerService = playerService;
        this.actionRepository = actionRepository;
    }
    @PostConstruct
    public void createActionSet() {
        Attack attack = new Attack("공격");
        Defense defense = new Defense("방어");
        Skill doubleSlice = new Skill("두 번 베기", 2, 2);
        Skill tripleSlice = new Skill("세 번 베기", 3, 2);
        Skill CriticalHit = new Skill("세게 때리기", 5, 3);

        List<Action> actions = new ArrayList<>(List.of(attack, defense, doubleSlice, tripleSlice, CriticalHit));
        actionRepository.saveActions(actions);
    }

    @Transactional
    public List<Action> getActions() {
        return actionRepository.findAll();
    }
    public Action getAction(int idx) {
        return actionRepository.findByIdx(idx);
    }

    @Transactional
    public String printActions(String playerName) {
        List<Action> actions = actionRepository.findAll();
        Player player = playerService.getPlayer(playerName);
        int i=1;
        for (Action action : actions) {
            if (action instanceof BasicAction) {
                BasicAction basicAction = (BasicAction) action;
                return (i + ". " + basicAction.getName() + "(" + basicAction.getMinDamage()
                        + " ~ " + basicAction.getMaxDamage() + ")");
            }
            else if (action instanceof Skill) {
                Skill skill = (Skill) action;
                return (i + ". " + skill.getName() + "(" + skill.getMinDamage()
                        + " ~ " + skill.getMaxDamage() + ")" + " - " + skill.getMpCost() + "MP - "
                        + player.getSkillCooldowns().get(skill.getName()) + "턴");
            }
            i++;
        }
        return null;
    }

}
