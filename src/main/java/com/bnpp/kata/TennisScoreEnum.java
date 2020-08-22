package com.bnpp.kata;

import com.bnpp.kata.constants.GameConstants;
import com.bnpp.kata.exception.TennisException;

import java.util.Arrays;

public enum TennisScoreEnum {
    LOVE("Love" , 0), FIFTEEN("Fifteen" , 1), THIRTY("Thirty" , 2), FORTY("Forty" , 3);

    String score;
    int point;

    TennisScoreEnum(String score , int point) {
        this.score = score;
        this.point = point;
    }

    public static TennisScoreEnum fromScore(int point) {
        if (point < GameConstants.ZERO_POINT || point > GameConstants.THREE_POINT)
            throw new TennisException(GameConstants.INVALID_TENNIS_POINT);

        return Arrays.stream(TennisScoreEnum.values()).filter(tennisScore -> tennisScore.point == point).findFirst().orElse(null);
    }
}
