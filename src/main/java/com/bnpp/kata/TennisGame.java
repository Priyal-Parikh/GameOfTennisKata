package com.bnpp.kata;

import com.bnpp.kata.constants.GameConstants;
import com.bnpp.kata.entity.TennisPlayer;
import com.bnpp.kata.exception.TennisException;

public class TennisGame {
    private TennisPlayer firstPlayer;
    private TennisPlayer secondPlayer;

    public TennisGame(TennisPlayer firstPlayer , TennisPlayer secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public TennisPlayer getFirstPlayer() {
        return firstPlayer;
    }

    public TennisPlayer getSecondPlayer() {
        return secondPlayer;
    }

    public String getCurrentGameScore() {
        String currentGameScore;
        String firstPlayerTennisScore = getTennisScoreFromPoints(firstPlayer.getScoredPoint());
        String secondPlayerTennisScore = getTennisScoreFromPoints(secondPlayer.getScoredPoint());

        if (firstPlayerTennisScore.equalsIgnoreCase(secondPlayerTennisScore))
            currentGameScore = firstPlayerTennisScore + GameConstants.COLON + GameConstants.ALL;
        else
            currentGameScore = firstPlayerTennisScore + GameConstants.COLON + secondPlayerTennisScore;

        return currentGameScore;
    }

    public void increasePlayerScore(String pointWinnerPlayer) {
        if (isInvalidPlayerName(pointWinnerPlayer)) throw new TennisException("Incorrect Player Name");
        if (pointWinnerPlayer.equalsIgnoreCase(firstPlayer.getName()))
            firstPlayer.setScoredPoint(firstPlayer.getScoredPoint()+GameConstants.ONE_POINT);
        else secondPlayer.setScoredPoint(firstPlayer.getScoredPoint()+GameConstants.ONE_POINT);
    }

    private boolean isInvalidPlayerName(String playerName) {
        return !isPlayerNameNotNull(playerName) || (!playerName.equalsIgnoreCase(firstPlayer.getName()) && (!playerName.equalsIgnoreCase(secondPlayer.getName())));
    }

    private boolean isPlayerNameNotNull(String playerName) {
        return !(null == playerName) && !("".equals(playerName));
    }

    private String getTennisScoreFromPoints(int pointsScored) {
        String tennisScore = "";

        if (pointsScored == GameConstants.ZERO_POINT) tennisScore = GameConstants.SCORE_LOVE;
        else if (pointsScored == GameConstants.ONE_POINT) tennisScore = GameConstants.SCORE_FIFTEEN;

        return tennisScore;
    }
}