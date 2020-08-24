package com.bnppf.kata.interfaces;

import com.bnppf.kata.entity.TennisPlayer;

public interface TennisGameInterface {
    void increasePlayerScore(String pointWinnerPlayer);
    String getCurrentGameScore();
    TennisPlayer getFirstPlayer();
    TennisPlayer getSecondPlayer();
}
