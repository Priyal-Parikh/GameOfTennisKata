package com.bnppf.kata.enums;

import com.bnppf.kata.constants.GameConstants;
import com.bnppf.kata.exception.TennisException;

import java.util.Arrays;

public enum TennisScoreEnum {
    LOVE("Love" , 0), FIFTEEN("Fifteen" , 1), THIRTY("Thirty" , 2), FORTY("Forty" , 3);

    private String score;
    int point;

    TennisScoreEnum(String score , int point) {
        this.setScore(score);
        this.point = point;
    }

    public static TennisScoreEnum fromScore(int point) {
        if (point < GameConstants.ZERO_POINT || point > GameConstants.THREE_POINT)
            throw new TennisException(GameConstants.INVALID_TENNIS_POINT);

        return Arrays.stream(TennisScoreEnum.values()).filter(tennisScore -> tennisScore.point == point).findFirst().orElse(null);
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
