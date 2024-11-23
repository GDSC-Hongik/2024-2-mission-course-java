package com.gdsc.game.behavior;

// interface : 모든 행동(공격, 방어, 스킬)에 대한 공통 인터페이스
public interface Behavior {
    void execute(Character MyChar, Character OpponentChar);
}