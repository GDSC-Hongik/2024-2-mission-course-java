package com.gdsc.game;

import java.util.Random;

public class Character {
    private String name;
    private int health;
    private int mana;
    private Skill[] skills;

    // 생성자
    public Character(String name, int health, int mana, Skill[] skills) {
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.skills = skills;
    }

    // 공격
    public int attack(){
        Random random = new Random();
        return random.nextInt(10)+1;
    }

    // 방어
    public int defend(){
        Random random = new Random();
        return random.nextInt(10)+1;
    }

    // 스킬 사용
    public int useSkill(Skill skill){
        if(skill.isAvailable() && this.mana >= skill.getManaCost()){
            this.mana -= skill.getManaCost();
            skill.useCooldown();
            return skill.calculateDamage();
        }
        return 0;
    }

    // 체력 감소
    public void decreaseHealth(int damage){
        this.health -= damage;
    }

    // Getter
    // 이름 getter
    public String getName(){
        return this.name;
    }
    // 체력 getter
    public int getHealth(){
        return this.health;
    }
    // 마나 getter
    public int getMana(){
        return this.mana;
    }
    // 스킬 목록 getter
    public Skill[] getSkills() {
        return this.skills;
    }
}