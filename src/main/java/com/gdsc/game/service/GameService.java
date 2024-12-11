package com.gdsc.game.service;

import com.gdsc.game.model.ActionRequest;
import com.gdsc.game.model.GameStatus;
import com.gdsc.game.game.GameManager;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameManager gameManager = new GameManager();

    public GameStatus initializeGame(GameStatus request) {
        return gameManager.initialize(request.getPlayers(), request.getTurns());
    }

    public GameStatus performAction(ActionRequest request) {
        return gameManager.performAction(
                request.getAttacker(), request.getDefender(), request.getActionType(), request.getSkillIndex());
    }

    public GameStatus getGameStatus() {
        return gameManager.getCurrentStatus();
    }
}
