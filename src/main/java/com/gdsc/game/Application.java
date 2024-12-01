package com.gdsc.game;


import java.util.Scanner;

public class Application{

    public static void main(String[] args) {
        System.out.println("write two character");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        String[] names = input.split(",");
        String name1 = names[0];
        String name2 = names[1];

        Character A = new Character(name1);
        Character B = new Character(name2);

        System.out.println("How many turns?");
        int turn = sc.nextInt();

        Game runGame = new Game(A,B,turn);
        runGame.runGame();

        sc.close();

    }

}
