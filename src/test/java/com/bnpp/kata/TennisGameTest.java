package com.bnpp.kata;

import com.bnpp.kata.constants.GameConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TennisGameTest {

    TennisGame tennisGame;

    @Before
    public void initialSetup() {
        tennisGame=new TennisGame(GameConstants.FIRST_PLAYER , GameConstants.SECOND_PLAYER);
    }

    @Test
    public void initializeNewTennisGame() {
        Assert.assertNotNull(tennisGame);
    }

    @Test
    public void startNewGameWithTwoPlayers() {
        Assert.assertEquals(GameConstants.FIRST_PLAYER , tennisGame.getFirstPlayerName());
        Assert.assertEquals(GameConstants.SECOND_PLAYER , tennisGame.getSecondPlayerName());
    }

    @Test
    public void initialScoreShouldBeLoveAll() {
        Assert.assertEquals(GameConstants.SCORE_LOVE + GameConstants.COLON + GameConstants.ALL , tennisGame.getCurrentGameScore());
    }

    @Test
    public void firstPlayerScoreShouldIncreaseAfterWinningAPoint() {
        tennisGame.increasePlayerScore(GameConstants.FIRST_PLAYER);

        Assert.assertEquals(GameConstants.ONE_POINT,tennisGame.getFirstPlayerScore());
    }

    @Test
    public void secondPlayerScoreShouldIncreaseAfterWinningAPoint() {
        tennisGame.increasePlayerScore(GameConstants.SECOND_PLAYER);

        Assert.assertEquals(GameConstants.ONE_POINT,tennisGame.getSecondPlayerScore());
    }
}
