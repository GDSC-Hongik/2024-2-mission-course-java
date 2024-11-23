package com.gdsc.game;

public class CutThreeTimes extends SkillAction{
    private static final int MIN_DAMAGE = 5;
    private static final int MAX_DAMAGE = 50;

    private static final int MANA_COST = 3;
    private static final int COOL_TIME = 0;

    public CutThreeTimes() {
        super(MANA_COST,COOL_TIME);
    }

    @Override
    public void execute(Character current, Character target) {
        current.useMana(manaCost);
        int damage = random.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
        int actualDamage = manaCost * damage;
        target.takeDamage(actualDamage);
        remainingCooltime = coolTime;
    }
}
