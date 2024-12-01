package com.gdsc.game.model;

import lombok.Data;

@Data
public class TurnStatus {
    private int currentTurn;
    private String activePlayer;
    private String waitingPlayer;
    private boolean gameOver;

    public TurnStatus(int currentTurn, String activePlayer, String waitingPlayer, boolean gameOver) {
        this.currentTurn = currentTurn;
        this.activePlayer = activePlayer;
        this.waitingPlayer = waitingPlayer;
        this.gameOver = gameOver;
    }
}

