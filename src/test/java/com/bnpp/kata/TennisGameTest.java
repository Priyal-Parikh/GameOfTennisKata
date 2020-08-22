package com.bnpp.kata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TennisGameTest {
    TennisGame tennisGame;

    @Before
    public void initialSetup() {
        tennisGame=new TennisGame("Serena Williams","Maria Sharapova");
    }

    @Test
    public void initializeNewTennisGame() {
        Assert.assertNotNull(tennisGame);
    }

    @Test
    public void startNewGameWithTwoPlayers() {
        Assert.assertEquals("Serena Williams" , tennisGame.getFirstPlayerName());
        Assert.assertEquals("Maria Sharapova" , tennisGame.getSecondPlayerName());
    }

    @Test
    public void initialScoreShouldBeLoveAll() {
        Assert.assertEquals("Love All",tennisGame.getCurrentGameScore());
    }
}
