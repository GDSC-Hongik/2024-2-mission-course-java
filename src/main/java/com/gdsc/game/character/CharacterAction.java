package com.gdsc.game.character;

public interface CharacterAction {
    int attack();
    void defend();
    int useSkill(int index);
    void takeDamage(int damage);
    void updateSkills();
    String getStatus(CharacterAction other);
    String getName();
    int getHp();
}
