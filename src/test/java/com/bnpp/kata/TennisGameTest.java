package com.bnpp.kata;

import com.bnpp.kata.constants.GameConstants;
import com.bnpp.kata.entity.TennisPlayer;
import com.bnpp.kata.exception.TennisException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TennisGameTest {

    TennisGame tennisGame;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void initialSetup() {
        tennisGame = new TennisGame(new TennisPlayer(GameConstants.FIRST_PLAYER) , new TennisPlayer(GameConstants.SECOND_PLAYER));
    }

    @Test
    public void initializeNewTennisGame() {
        Assert.assertNotNull(tennisGame);
    }

    @Test
    public void startNewGameWithTwoPlayers() {
        Assert.assertEquals(GameConstants.FIRST_PLAYER , tennisGame.getFirstPlayer().getName());
        Assert.assertEquals(GameConstants.SECOND_PLAYER , tennisGame.getSecondPlayer().getName());
    }

    @Test
    public void initialScoreShouldBeLoveAll() {
        Assert.assertEquals(GameConstants.SCORE_LOVE + GameConstants.COLON + GameConstants.ALL , tennisGame.getCurrentGameScore());
    }

    @Test
    public void firstPlayerScoreShouldIncreaseAfterWinningAPoint() {
        tennisGame.increasePlayerScore(GameConstants.FIRST_PLAYER);

        Assert.assertEquals(GameConstants.ONE_POINT , tennisGame.getFirstPlayer().getScoredPoint());
    }

    @Test
    public void secondPlayerScoreShouldIncreaseAfterWinningAPoint() {
        tennisGame.increasePlayerScore(GameConstants.SECOND_PLAYER);

        Assert.assertEquals(GameConstants.ONE_POINT , tennisGame.getSecondPlayer().getScoredPoint());
    }

    @Test
    public void shouldThrowAnExceptionIfNameIsNotCorrect() {
        exceptionRule.expect(TennisException.class);
        exceptionRule.expectMessage(GameConstants.INCORRECT_PLAYER_NAME);

        tennisGame.increasePlayerScore(GameConstants.RANDOM_PLAYER);
    }

    @Test
    public void scoreShouldBeLoveFifteenIfSecondPlayerScoresPoint() {
        tennisGame.increasePlayerScore(GameConstants.SECOND_PLAYER);
        tennisGame.getCurrentGameScore();

        Assert.assertEquals(GameConstants.SCORE_LOVE + GameConstants.COLON + GameConstants.SCORE_FIFTEEN , tennisGame.getCurrentGameScore());
    }


    @Test
    public void scoreShouldReturnFifteenAllIfBothPlayerWinsFirstPoint() {
        prepareScore(GameConstants.ONE_POINT , GameConstants.ONE_POINT);

        Assert.assertEquals(GameConstants.SCORE_FIFTEEN + GameConstants.COLON + GameConstants.ALL , tennisGame.getCurrentGameScore());
    }

    @Test
    public void scoreShouldBeThirtyFifteenIfFirstPlayerScoresTwoAndSecondPlayerScoresOnePoint() {
        prepareScore(GameConstants.TWO_POINT , GameConstants.ONE_POINT);

        Assert.assertEquals(GameConstants.SCORE_THIRTY + GameConstants.COLON + GameConstants.SCORE_FIFTEEN , tennisGame.getCurrentGameScore());
    }

    private void prepareScore(int firstPlayerPoints , int secondPlayerPoints) {
        for (int counter = 0; counter < firstPlayerPoints; counter++)
            tennisGame.increasePlayerScore(GameConstants.FIRST_PLAYER);
        for (int counter = 0; counter < secondPlayerPoints; counter++)
            tennisGame.increasePlayerScore(GameConstants.SECOND_PLAYER);
    }
}
