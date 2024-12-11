package com.gdsc.game.character;

import com.gdsc.game.skill.Skill;
import com.gdsc.game.skill.SkillAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameCharacter implements CharacterAction {
    private String name;
    private int hp;
    private int mp;  // 마나
    private int defense;  // 방어력
    private List<SkillAction> skills;
    private Random random;

    public GameCharacter(String name, int hp, int mp) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.defense = 0;
        this.random = new Random();
        this.skills = new ArrayList<>();
        initSkills();
    }

    private void initSkills() {
        skills.add(new Skill("CuttingTwice", 2, 0));
        skills.add(new Skill("CuttingThreeTimes", 3, 0));
        skills.add(new Skill("HittingHard", 5, 2));
    }

    @Override
    public int attack() {
        return random.nextInt(10) + 1;
    }

    @Override
    public void defend() {
        defense = random.nextInt(10) + 1;
    }

    @Override
    public int useSkill(int index) {
        SkillAction skill = skills.get(index);
        int damage = skill.use(mp);
        if (damage > 0) {
            mp -= skill.getManaCost();
        }
        return damage;
    }

    @Override
    public void takeDamage(int damage) {
        hp -= Math.max(0, damage - defense);
        defense = 0;
    }

    @Override
    public void updateSkills() {
        skills.forEach(SkillAction::reduceCooldown);
    }

    @Override
    public String getStatus(CharacterAction other) {
        return String.format("%s hp : %d mp: %d | %s hp: %d mp: %d\n" +
                        "1. Attack(1 ~ 10)\n" +
                        "2. Defend(1 ~ 10)\n" +
                        "3. %s\n4. %s\n5. %s",
                name, hp, mp, other.getName(), other.getHp(), other.getMp(),
                skills.get(0).getInfo(), skills.get(1).getInfo(), skills.get(2).getInfo());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHp() {
        return hp;
    }

    // 추가된 메서드: getMp()와 getDefense()
    public int getMp() {
        return mp;
    }

    public int getDefense() {
        return defense;
    }
}
