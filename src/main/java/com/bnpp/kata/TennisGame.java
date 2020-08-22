package com.bnpp.kata;

public class TennisGame {
    public static final String SCORE_LOVE = "Love";
    public static final String ALL = "All";
    public static final String COLON = " : ";
    private String firstPlayerName;
    private String secondPlayerName;

    public TennisGame(String firstPlayerName , String secondPlayerName) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public String getCurrentGameScore() {
        return SCORE_LOVE + COLON + ALL;
    }
}