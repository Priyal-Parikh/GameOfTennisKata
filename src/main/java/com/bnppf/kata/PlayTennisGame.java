package com.bnppf.kata;

import com.bnppf.kata.entity.TennisPlayer;
import com.bnppf.kata.exception.TennisException;
import com.bnppf.kata.interfaces.TennisGameInterface;

import java.util.Scanner;

public class PlayTennisGame {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TennisGameInterface tennisGame = startNewGame();
        playTennisGame(tennisGame);
        scanner.close();
    }

    private static TennisGameInterface startNewGame() {
        String firstPlayer;
        String secondPlayer;

        do {
            System.out.println("Kindly enter two different players names to start the game.");

            System.out.print("Player 1 name : ");
            firstPlayer = scanner.nextLine().trim();
            System.out.println("Player 1: " + firstPlayer);

            System.out.print("Player 2 name : ");
            secondPlayer = scanner.nextLine().trim();
            System.out.println("Player 2: " + secondPlayer);

        } while ("".equals(firstPlayer) || "".equals(secondPlayer) || firstPlayer.equalsIgnoreCase(secondPlayer));

        return new TennisGame(new TennisPlayer(firstPlayer) , new TennisPlayer(secondPlayer));
    }

    private static void playTennisGame(TennisGameInterface tennisGame) {

        System.out.println("Game  Starts !!!");

        while (!tennisGame.getCurrentGameScore().contains("Winner")) {
            System.out.print("Next point scored by :");
            String pointScoredByPlayer = scanner.nextLine();
            try {
                tennisGame.increasePlayerScore(pointScoredByPlayer);
            } catch (TennisException e) {
                System.out.println("Exception occurred: " + e.getMessage());
            }
            displayScoreBoard(tennisGame);
        }

        System.out.println("Game over!");
    }

    private static void displayScoreBoard(TennisGameInterface tennisGame) {
        String gameScore = tennisGame.getCurrentGameScore();
        System.out.println("//+++++++++++++++++++++++++++++++//");
        System.out.println("----------" + gameScore + "------------");
        System.out.println("//+++++++++++++++++++++++++++++++//");
    }
}
