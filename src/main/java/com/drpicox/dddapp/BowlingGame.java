package com.drpicox.dddapp;

public class BowlingGame {

    private int score = 0;

    public void roll(int pins) {
        score += pins;
    }

    public int score() {
        return score;
    }
}
