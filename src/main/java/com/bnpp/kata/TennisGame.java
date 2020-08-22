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
        else if (checkForAdvantage())
            currentGameScore = GameConstants.SCORE_ADVANTAGE + GameConstants.COLON + getHighestScorer();
        else if (hasAnyPlayerScoreBeyondForty() && hasPointDifferenceMoreThanOne())
            currentGameScore = GameConstants.SCORE_WINS + GameConstants.COLON + getHighestScorer();
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
        TennisScoreEnum firstPlayerTennisScore = getTennisScoreFromPoints(firstPlayer.getScoredPoint());
        TennisScoreEnum secondPlayerTennisScore = getTennisScoreFromPoints(secondPlayer.getScoredPoint());

        return isSameScore() ?
                firstPlayerTennisScore.score + GameConstants.COLON + GameConstants.ALL :
                firstPlayerTennisScore.score + GameConstants.COLON + secondPlayerTennisScore.score;
    }

    private boolean isInvalidPlayerName(String playerName) {
        return isPlayerNameNull(playerName) || isPlayerNotExists(playerName);
    }

    private boolean isPlayerNotExists(String playerName) {
        return !playerName.equalsIgnoreCase(firstPlayer.getName()) && (!playerName.equalsIgnoreCase(secondPlayer.getName()));
    }

    private boolean isPlayerNameNull(String playerName) {
        return (null == playerName) || ("".equals(playerName));
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

    private boolean hasAnyPlayerScoreBeyondForty() {
        return firstPlayer.getScoredPoint() > GameConstants.THREE_POINT || secondPlayer.getScoredPoint() > GameConstants.THREE_POINT;
    }

    private boolean hasSinglePointDifference() {
        return Math.abs(secondPlayer.getScoredPoint() - firstPlayer.getScoredPoint()) == GameConstants.ONE_POINT;
    }

    private boolean checkForAdvantage() {
        return hasAnyPlayerScoreBeyondForty() && hasSinglePointDifference();
    }

    private String getHighestScorer() {
        return firstPlayer.getScoredPoint() > secondPlayer.getScoredPoint() ? firstPlayer.getName() : secondPlayer.getName();
    }

    private boolean hasPointDifferenceMoreThanOne() {
        return Math.abs(secondPlayer.getScoredPoint() - firstPlayer.getScoredPoint()) > GameConstants.ONE_POINT;
    }
}