package com.gdsc.game.action.skill;

import com.gdsc.game.action.Action;
import com.gdsc.game.manager.ActionResult;
import com.gdsc.game.player.Player;

import java.util.Random;

public class Skill implements Action {
    private static final int MIN_DAMAGE = 1;
    private static final int MAX_DAMAGE = 10;
    private String name;
    private int mpCost;
    private int coolDownTurns;
    private int damage;

    public int getMaxDamage() {return MAX_DAMAGE;}
    public int getMinDamage() {return MIN_DAMAGE;}
    public int getMpCost() { return mpCost; }

    public Skill(String name, int mpCost, int coolDownTurns) {
        this.name = name;
        this.mpCost = mpCost;
        this.coolDownTurns = coolDownTurns;
        this.damage = calculateSkillDamage(mpCost);
    }
    private int calculateSkillDamage(int mpCost) {
        int baseDamage = new Random().nextInt(MAX_DAMAGE- MIN_DAMAGE + 1) + MIN_DAMAGE; // 기본 1~10 데미지
        return baseDamage * mpCost;
    }

    @Override
    public String getName() {
        return name;
    }
    public int getCoolDownTurns() {
        return coolDownTurns;
    }



    @Override
    public ActionResult execute(Player user, Player target) {
        user.consumeMp(mpCost);
        return new ActionResult("Attack", damage, user.getName() + "이(가) " + target.getName() + " 에게 스킬 공격! 데미지는 " + damage + "!");
    }

}
