package com.gdsc.game.player;

import com.gdsc.game.action.Action;
import com.gdsc.game.action.skill.Skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private String name;
    private int hp = 50;
    private int mp = 30;

    private List<Action> actions;  // 플레이어의 모든 가능한 행동
    private List<Skill> skills;  // 플레이어가 가진 스킬 리스트
    private Map<String, Integer> skillCooldowns = new HashMap<>();  // 각 스킬의 쿨타임 관리

    public Player(String name, List<Action> actions, List<Skill> skills) {
        this.name = name;
        this.actions = actions;
        this.skills = skills;
    }

    public String getName() {return name;}
    public int getHp() {return hp;}
    public int getMp() {return mp;}
    public List<Action> getActions() {return actions;}
    public Map<String, Integer> getSkillCooldowns() {return skillCooldowns;}

    public void applyDamage(int damage) {this.hp = Math.max(0, hp - damage);}
    public void consumeMp(int mpCost) {this.mp = Math.max(0, mp - mpCost);}

    public void updateCooldowns() {  // 쿨타임 차감
        for (String skillName : skillCooldowns.keySet()) {
            int remainingTurns = skillCooldowns.get(skillName);

            if (remainingTurns > 0) {
                skillCooldowns.put(skillName, remainingTurns - 1);
            }
        }
    }
    public boolean canUseSkill(String skillName) { // 스킬 쿨타임 찼는지 여부 확인, 불린형 리턴
        return skillCooldowns.getOrDefault(skillName, 0) == 0;
    }
    public void applySkillCooldown(Skill skill) {
        skillCooldowns.put(skill.getName(), skill.getCoolDownTurns());  // 스킬 쿨타임 설정
    }


}
