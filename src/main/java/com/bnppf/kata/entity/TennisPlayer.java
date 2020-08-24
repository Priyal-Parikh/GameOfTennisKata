package com.bnppf.kata.entity;

public class TennisPlayer {
    private String name;
    private int scoredPoint;

    public TennisPlayer(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public int getScoredPoint() {
        return scoredPoint;
    }

    public void setScoredPoint(int scoredPoint) {
        this.scoredPoint = scoredPoint;
    }
}
