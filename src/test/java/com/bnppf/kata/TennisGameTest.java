package com.bnppf.kata;

import com.bnppf.kata.constants.TestConstants;
import com.bnppf.kata.entity.TennisPlayer;
import com.bnppf.kata.exception.TennisException;
import com.bnppf.kata.interfaces.TennisGameInterface;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TennisGameTest {

    TennisGameInterface tennisGame;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void initialSetup() {
        tennisGame = new TennisGame(new TennisPlayer(TestConstants.FIRST_PLAYER) , new TennisPlayer(TestConstants.SECOND_PLAYER));
    }

    @Test
    public void initializeNewTennisGame() {
        Assert.assertNotNull(tennisGame);
    }

    @Test
    public void startNewGameWithTwoPlayers() {
        Assert.assertEquals(TestConstants.FIRST_PLAYER , tennisGame.getFirstPlayer().getName());
        Assert.assertEquals(TestConstants.SECOND_PLAYER , tennisGame.getSecondPlayer().getName());
    }

    @Test
    public void initialScoreShouldBeLoveAll() {
        Assert.assertEquals(TestConstants.SCORE_LOVE + TestConstants.COLON + TestConstants.ALL , tennisGame.getCurrentGameScore());
    }

    @Test
    public void firstPlayerScoreShouldIncreaseAfterWinningAPoint() {
        tennisGame.increasePlayerScore(TestConstants.FIRST_PLAYER);

        Assert.assertEquals(TestConstants.ONE_POINT , tennisGame.getFirstPlayer().getScoredPoint());
    }

    @Test
    public void secondPlayerScoreShouldIncreaseAfterWinningAPoint() {
        tennisGame.increasePlayerScore(TestConstants.SECOND_PLAYER);

        Assert.assertEquals(TestConstants.ONE_POINT , tennisGame.getSecondPlayer().getScoredPoint());
    }

    @Test
    public void shouldThrowAnExceptionIfNameIsNotCorrect() {
        exceptionRule.expect(TennisException.class);
        exceptionRule.expectMessage(TestConstants.INCORRECT_PLAYER_NAME);

        tennisGame.increasePlayerScore(TestConstants.RANDOM_PLAYER);
    }

    @Test
    public void scoreShouldBeLoveFifteenIfSecondPlayerScoresPoint() {
        tennisGame.increasePlayerScore(TestConstants.SECOND_PLAYER);
        tennisGame.getCurrentGameScore();

        Assert.assertEquals(TestConstants.SCORE_LOVE + TestConstants.COLON + TestConstants.SCORE_FIFTEEN , tennisGame.getCurrentGameScore());
    }


    @Test
    public void scoreShouldReturnFifteenAllIfBothPlayerWinsFirstPoint() {
        prepareScore(TestConstants.ONE_POINT , TestConstants.ONE_POINT);

        Assert.assertEquals(TestConstants.SCORE_FIFTEEN + TestConstants.COLON + TestConstants.ALL , tennisGame.getCurrentGameScore());
    }

    @Test
    @Parameters({
            "0, 0, Love : All" ,
            "0, 1, Love : Fifteen" ,
            "0, 2, Love : Thirty" ,
            "0, 3, Love : Forty" ,
            "1, 0, Fifteen : Love" ,
            "1, 1, Fifteen : All" ,
            "1, 2, Fifteen : Thirty" ,
            "1, 3, Fifteen : Forty" ,
            "2, 0, Thirty : Love" ,
            "2, 1, Thirty : Fifteen" ,
            "2, 2, Thirty : All" ,
            "2, 3, Thirty : Forty" ,
            "3, 0, Forty : Love" ,
            "3, 1, Forty : Fifteen" ,
            "3, 2, Forty : Thirty"
    })
    public void scoreShouldBeAsPerParameters(int firstPlayerPoints , int secondPlayerPoints , String currentGameScore) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(currentGameScore , tennisGame.getCurrentGameScore());
    }

    @Test
    @Parameters({
            "4, 4" ,
            "5, 5" ,
            "15, 15" ,
            "26, 26"
    })
    public void checkForDeuceSituationInGame(int firstPlayerPoints , int secondPlayerPoints) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(TestConstants.SCORE_DEUCE , tennisGame.getCurrentGameScore());
    }

    @Test
    @Parameters({
            "4, 5" ,
            "14, 15" ,
            "27, 28" ,
            "6 , 7"
    })
    public void checkForAdvantageSituationForPlayerTwo(int firstPlayerPoints , int secondPlayerPoints) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(TestConstants.SCORE_ADVANTAGE + TestConstants.COLON + TestConstants.SECOND_PLAYER , tennisGame.getCurrentGameScore());
    }

    @Test
    @Parameters({
            "5, 4" ,
            "15, 14" ,
            "22, 21"
    })
    public void checkForAdvantageSituationForFirstPlayer(int firstPlayerPoints , int secondPlayerPoints) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(TestConstants.SCORE_ADVANTAGE + TestConstants.COLON + TestConstants.FIRST_PLAYER , tennisGame.getCurrentGameScore());
    }

    @Test
    @Parameters({
            "5, 3" ,
            "8, 6" ,
            "15, 13" ,
            "22, 20"
    })
    public void shouldReturnFirstPlayerAsWinner(int firstPlayerPoints , int secondPlayerPoints) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(TestConstants.SCORE_WINS + TestConstants.COLON + TestConstants.FIRST_PLAYER , tennisGame.getCurrentGameScore());
    }

    @Test
    @Parameters({
            "5, 8" ,
            "8, 5" ,
            "15, 18 " ,
            "22, 29 "
    })
    public void shouldThrowAnExceptionForInvalidScore(int firstPlayerPoints , int secondPlayerPoints) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);
        exceptionRule.expect(TennisException.class);
        exceptionRule.expectMessage(TestConstants.INVALID_SCORE);

        tennisGame.getCurrentGameScore();
    }

    private void prepareScore(int firstPlayerPoints , int secondPlayerPoints) {
        for (int counter = 0; counter < firstPlayerPoints; counter++)
            tennisGame.increasePlayerScore(TestConstants.FIRST_PLAYER);
        for (int counter = 0; counter < secondPlayerPoints; counter++)
            tennisGame.increasePlayerScore(TestConstants.SECOND_PLAYER);
    }
}
