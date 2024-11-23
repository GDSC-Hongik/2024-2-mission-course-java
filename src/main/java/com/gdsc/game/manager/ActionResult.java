package com.gdsc.game.manager;

public class ActionResult {
    private final String actionName;
    private final int damage; // 양수 : 공격, 음수 : 방어
    private final String message;

    public ActionResult(String actionName, int damage, String message) {
        this.actionName = actionName;
        this.damage = damage;
        this.message = message;
    }

    public String getActionName() {
        return actionName;
    }
    public int getDamage() {
        return damage;
    }
    public String getMessage() {
        return message;
    }

}
