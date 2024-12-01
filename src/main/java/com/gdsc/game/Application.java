package com.gdsc.game;

import com.gdsc.game.service.GameService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        GameService gameService = context.getBean(GameService.class);
        gameService.startGame();
    }
}
