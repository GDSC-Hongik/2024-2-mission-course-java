package com.gdsc.game.dto;

public class CharacterStatusResponse {
    private String name;
    private int healthPoint;
    private int manaPoint;

    public CharacterStatusResponse(String name, int healthPoint, int manaPoint) {
        this.name = name;
        this.healthPoint = healthPoint;
        this.manaPoint = manaPoint;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getManaPoint() {
        return manaPoint;
    }

    @Override
    public String toString() {
        return name + " 체력: " + healthPoint + " 마나: " + manaPoint;
    }
}
