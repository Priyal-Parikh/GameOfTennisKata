package com.bnppf.kata;

import com.bnppf.kata.constants.GameConstants;
import com.bnppf.kata.entity.TennisPlayer;
import com.bnppf.kata.enums.TennisScoreEnum;
import com.bnppf.kata.exception.TennisException;
import com.bnppf.kata.interfaces.TennisGameInterface;

public class TennisGame implements TennisGameInterface {
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

        if (checkForInvalidScore()) {
            throw new TennisException(GameConstants.INVALID_SCORE);
        }

        if (checkForDeuce()) {
            currentGameScore = GameConstants.SCORE_DEUCE;
        } else if (checkForAdvantage()) {
            currentGameScore = GameConstants.SCORE_ADVANTAGE + GameConstants.COLON + getHighestScorer();
        } else if (isGameHasWinner()) {
            currentGameScore = GameConstants.SCORE_WINS + GameConstants.COLON + getHighestScorer();
        } else {
            currentGameScore = convertScore();
        }

        return currentGameScore;
    }

    public void increasePlayerScore(String pointWinnerPlayer) {
        if (isInvalidPlayerName(pointWinnerPlayer)) {
            throw new TennisException("Incorrect Player Name");
        }

        if (pointWinnerPlayer.equalsIgnoreCase(firstPlayer.getName())) {
            firstPlayer.setScoredPoint(firstPlayer.getScoredPoint() + GameConstants.ONE_POINT);
        } else {
            secondPlayer.setScoredPoint(secondPlayer.getScoredPoint() + GameConstants.ONE_POINT);
        }
    }

    private boolean isGameHasWinner() {
        return hasAnyPlayerScoreBeyondForty() && hasTwoPointDifference();
    }


    private String convertScore() {
        TennisScoreEnum firstPlayerTennisScore = getTennisScoreFromPoints(firstPlayer.getScoredPoint());
        TennisScoreEnum secondPlayerTennisScore = getTennisScoreFromPoints(secondPlayer.getScoredPoint());

        return isSameScore() ?
                firstPlayerTennisScore.getScore() + GameConstants.COLON + GameConstants.ALL :
                firstPlayerTennisScore.getScore() + GameConstants.COLON + secondPlayerTennisScore.getScore();
    }

    private boolean isPlayerNotExists(String playerName) {
        return !playerName.equalsIgnoreCase(firstPlayer.getName()) && (!playerName.equalsIgnoreCase(secondPlayer.getName()));
    }

    private boolean isPlayerExists(String playerName) {
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

    private boolean hasTwoPointDifference() {
        return Math.abs(secondPlayer.getScoredPoint() - firstPlayer.getScoredPoint()) == GameConstants.TWO_POINT;
    }

    private boolean checkForInvalidScore() {
        return hasAnyPlayerScoreBeyondForty() && hasMoreThanTwoPointDifference();
    }

    private boolean hasMoreThanTwoPointDifference() {
        return Math.abs(secondPlayer.getScoredPoint() - firstPlayer.getScoredPoint()) > GameConstants.TWO_POINT;
    }

    private boolean isInvalidPlayerName(String playerName) {
        return isPlayerExists(playerName) || isPlayerNotExists(playerName);
    }
}