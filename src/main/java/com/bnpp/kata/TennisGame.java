package com.bnpp.kata;

import com.bnpp.kata.constants.GameConstants;
import com.bnpp.kata.exception.TennisException;

public class TennisGame {
    private String firstPlayerName;
    private String secondPlayerName;
    private int firstPlayerScore;
    private int secondPlayerScore;

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

    public int getFirstPlayerScore() {
        return firstPlayerScore;
    }

    public int getSecondPlayerScore() {
        return secondPlayerScore;
    }

    public String getCurrentGameScore() {
        String currentGameScore;
        String firstPlayerTennisScore = getTennisScoreFromPoints(firstPlayerScore);
        String secondPlayerTennisScore = getTennisScoreFromPoints(secondPlayerScore);

        if (firstPlayerTennisScore.equalsIgnoreCase(secondPlayerTennisScore))
            currentGameScore = firstPlayerTennisScore + GameConstants.COLON + GameConstants.ALL;
        else
            currentGameScore = firstPlayerTennisScore + GameConstants.COLON + secondPlayerTennisScore;

        return currentGameScore;
    }

    public void increasePlayerScore(String pointWinnerPlayer) {
        if (isInvalidPlayerName(pointWinnerPlayer)) throw new TennisException("Incorrect Player Name");
        if (pointWinnerPlayer.equalsIgnoreCase(firstPlayerName))
            firstPlayerScore++;
        else secondPlayerScore++;
    }

    private boolean isInvalidPlayerName(String playerName) {
        return !isPlayerNameNotNull(playerName) || (!playerName.equalsIgnoreCase(firstPlayerName) && (!playerName.equalsIgnoreCase(secondPlayerName)));
    }

    private boolean isPlayerNameNotNull(String playerName) {
        return !(null == playerName) && !("".equals(playerName));
    }

    private String getTennisScoreFromPoints(int pointsScored) {
        String tennisScore = "";

        if (pointsScored == 0) tennisScore = GameConstants.SCORE_LOVE;
        else if (pointsScored == 1) tennisScore = GameConstants.SCORE_FIFTEEN;

        return tennisScore;
    }
}