package com.gdsc.game.dto;

public class ActionResponse {
    private String actionName;
    private String description;
    private int manaCost;
    private int remainingCooltime;
    private int remainingTurns;

    public ActionResponse(String actionName, String description, int manaCost, int remainingCooltime, int remainingTurns) {
        this.actionName = actionName;
        this.description = description;
        this.manaCost = manaCost;
        this.remainingCooltime = remainingCooltime;
        this.remainingTurns = remainingTurns;
    }

    public String getActionName() {
        return actionName;
    }

    public String getDescription() {
        return description;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getRemainingCooltime() {
        return remainingCooltime;
    }

    public int getRemainingTurns() {
        return remainingTurns;
    }
}

