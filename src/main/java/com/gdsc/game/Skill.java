package com.gdsc.game;

import java.util.Random;

public class Skill {
    private String name;
    private int manaCost;
    private int cooldown;

    // 생성자
    public Skill(String name, int manaCost, int cooldown) {
        this.name = name;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
    }

    // 데미지 계산
    public int calculateDamage(){
        Random rand = new Random();
        return this.manaCost * rand.nextInt(10)+1;
    }

    // 사용 가능 여부 확인
    // (쿨다운이 0이어야 사용 가능)
    public boolean isAvailable(){
        return this.cooldown == 0;
    }

    // 쿨다운 적용
    public void useCooldown(){
        Random rand = new Random();
        this.cooldown = rand.nextInt(2);
    }
    // 쿨다운 감소
    public void reduceCooldown(){
        if(this.cooldown > 0){
            this.cooldown--;
        }
    }

    // Getter
    // 이름 getter
    public String getName(){
        return this.name;
    }
    // 마나 소모량 getter
    public int getManaCost(){
        return this.manaCost;
    }
    // 쿨다운 getter
    public int getCooldown(){
        return this.cooldown;
    }
}
