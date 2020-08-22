package com.bnpp.kata;

import org.junit.Assert;
import org.junit.Test;

public class TennisGameTest {
    @Test
    public void initializeNewTennisGame() {
        TennisGame tennisGame = new TennisGame("Serena Williams" , "Maria Sharapova");

        Assert.assertNotNull(tennisGame);
    }

    @Test
    public void startNewGameWithTwoPlayers() {
        TennisGame tennisGame = new TennisGame("Serena Williams" , "Maria Sharapova");

        Assert.assertEquals("Serena Williams" , tennisGame.getFirstPlayerName());
        Assert.assertEquals("Maria Sharapova" , tennisGame.getSecondPlayerName());
    }
}
