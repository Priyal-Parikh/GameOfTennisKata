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

        if (checkForDeuce())
            currentGameScore = GameConstants.SCORE_DEUCE;
        else currentGameScore = convertScore();

        return currentGameScore;
    }

    public void increasePlayerScore(String pointWinnerPlayer) {
        if (isInvalidPlayerName(pointWinnerPlayer)) throw new TennisException("Incorrect Player Name");
        if (pointWinnerPlayer.equalsIgnoreCase(firstPlayer.getName()))
            firstPlayer.setScoredPoint(firstPlayer.getScoredPoint() + GameConstants.ONE_POINT);
        else secondPlayer.setScoredPoint(secondPlayer.getScoredPoint() + GameConstants.ONE_POINT);
    }

    private String convertScore() {
        String currentGameScore;
        TennisScoreEnum firstPlayerTennisScore = getTennisScoreFromPoints(firstPlayer.getScoredPoint());
        TennisScoreEnum secondPlayerTennisScore = getTennisScoreFromPoints(secondPlayer.getScoredPoint());

        if (firstPlayerTennisScore.score.equalsIgnoreCase(secondPlayerTennisScore.score))
            currentGameScore = firstPlayerTennisScore.score + GameConstants.COLON + GameConstants.ALL;
        else
            currentGameScore = firstPlayerTennisScore.score + GameConstants.COLON + secondPlayerTennisScore.score;
        return currentGameScore;
    }

    private boolean isInvalidPlayerName(String playerName) {
        return !isPlayerNameNotNull(playerName) || (!playerName.equalsIgnoreCase(firstPlayer.getName()) && (!playerName.equalsIgnoreCase(secondPlayer.getName())));
    }

    private boolean isPlayerNameNotNull(String playerName) {
        return !(null == playerName) && !("".equals(playerName));
    }

    private TennisScoreEnum getTennisScoreFromPoints(int pointsScored) {
        return TennisScoreEnum.fromScore(pointsScored);
    }

    private boolean checkForDeuce() {
        return isScoreBeyondThirty(firstPlayer) && isSameScore();
    }

    private boolean isSameScore() {
        return firstPlayer.getScoredPoint() == secondPlayer.getScoredPoint();
    }

    private boolean isScoreBeyondThirty(TennisPlayer tennisPlayer) {
        return tennisPlayer.getScoredPoint() >= GameConstants.THREE_POINT;
    }
}