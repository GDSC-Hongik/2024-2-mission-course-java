package com.gdsc.game.player;

import com.gdsc.game.action.skill.Skill;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@NoArgsConstructor
public class Player {
    private String name;
    private int hp = 50;
    private int mp = 30;
    private Map<String, Integer> skillCooldowns = new HashMap<>();  // 각 스킬의 쿨타임 관리
    private int turn = 1; // 현재 진행 턴 수

    public Player(String name) {
        this.name = name;
    }
    public String getName() {return name;}
    public int getHp() {return hp;}
    public int getMp() {return mp;}
    public Map<String, Integer> getSkillCooldowns() {return skillCooldowns;}
    public int getTurn() {return turn;}

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

    public void setSkillCooldowns(List<Skill> skills) {
        for (Skill skill : skills) {
            skillCooldowns.put(skill.getName(), 0);
        }
    }
    public String printSkillCooldowns() {
        String result = "";
        for (String skillName : skillCooldowns.keySet()) {
            result += skillName + ": " + skillCooldowns.get(skillName) + " | ";
        }
        return result;
    }
}
