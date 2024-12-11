package com.gdsc.game.model;

import lombok.Data;

@Data
public class GameStatus {
    private String[] players;
    private int turns;
    private String winner;
    private int currentTurn;
    private boolean gameOver;

    public GameStatus(String[] players, int turns, String winner, int currentTurn, boolean gameOver) {
        this.players = players;
        this.turns = turns;
        this.winner = winner;
        this.currentTurn = currentTurn;
        this.gameOver = gameOver;
    }
}
