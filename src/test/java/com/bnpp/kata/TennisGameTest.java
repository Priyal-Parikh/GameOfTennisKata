package com.bnpp.kata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TennisGameTest {
    public static final String FIRST_PLAYER = "Serena Williams";
    public static final String SECOND_PLAYER = "Maria Sharapova";
    public static final String SCORE_LOVE = "Love";
    public static final String ALL = "All";
    public static final String COLON = " : ";
    TennisGame tennisGame;

    @Before
    public void initialSetup() {
        tennisGame=new TennisGame(FIRST_PLAYER , SECOND_PLAYER);
    }

    @Test
    public void initializeNewTennisGame() {
        Assert.assertNotNull(tennisGame);
    }

    @Test
    public void startNewGameWithTwoPlayers() {
        Assert.assertEquals(FIRST_PLAYER , tennisGame.getFirstPlayerName());
        Assert.assertEquals(SECOND_PLAYER , tennisGame.getSecondPlayerName());
    }

    @Test
    public void initialScoreShouldBeLoveAll() {
        Assert.assertEquals(SCORE_LOVE + COLON + ALL , tennisGame.getCurrentGameScore());
    }
}
