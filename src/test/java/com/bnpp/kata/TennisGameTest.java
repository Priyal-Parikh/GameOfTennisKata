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
}
