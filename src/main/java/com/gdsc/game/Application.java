package com.gdsc.game;

import com.gdsc.game.game.Game;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input two players' names (comma-separated):");
        String[] players = scanner.nextLine().split(",");

        System.out.println("How many turns?");
        int turns = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt()

        Game game = new Game(players[0].trim(), players[1].trim(), turns);
        game.start();

        scanner.close();
    }
}
