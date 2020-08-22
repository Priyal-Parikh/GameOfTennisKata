package com.bnpp.kata;

import org.junit.Assert;
import org.junit.Test;

public class TennisGameTest
{
    @Test
    public void initializeNewTennisGame() {
        TennisGame tennisGame = new TennisGame();

        Assert.assertNotNull(tennisGame);
    }
}
