package com.gdsc.game.game;

import com.gdsc.game.model.TurnStatus;

public class TurnManager {
    private String player1;
    private String player2;
    private int totalTurns;
    private int currentTurn = 0;
    private String activePlayer;
    private boolean gameOver = false;

    public TurnManager(String player1, String player2, int totalTurns) {
        this.player1 = player1;
        this.player2 = player2;
        this.totalTurns = totalTurns;
        this.activePlayer = player1;
    }

    // 턴을 진행하는 메서드
    public void advanceTurn() {
        if (currentTurn >= totalTurns) {
            gameOver = true;
            return;
        }

        activePlayer = activePlayer.equals(player1) ? player2 : player1;
        currentTurn++;
    }

    // 게임을 종료하고 승자를 설정하는 메서드
    public void setGameOver(String winner) {
        gameOver = true;
    }

    // 현재 턴과 관련된 상태를 반환하는 메서드
    public TurnStatus getTurnStatus() {
        return new TurnStatus(
                currentTurn,
                activePlayer,
                activePlayer.equals(player1) ? player2 : player1,
                gameOver
        );
    }

    // 총 턴 수 반환
    public int getTotalTurns() {
        return totalTurns;
    }

    // 현재 턴 반환 (추가된 메서드)
    public int getCurrentTurn() {
        return currentTurn;
    }
}
