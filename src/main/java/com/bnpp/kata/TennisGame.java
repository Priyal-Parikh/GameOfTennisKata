package com.bnpp.kata;

public class TennisGame {
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
        return "Love All";
    }
}