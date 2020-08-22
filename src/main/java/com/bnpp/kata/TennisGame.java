package com.bnpp.kata;
import com.bnpp.kata.constants.GameConstants;

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
        return GameConstants.SCORE_LOVE + GameConstants.COLON + GameConstants.ALL;
    }
}