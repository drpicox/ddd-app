package com.drpicox.dddapp;

import org.springframework.stereotype.Component;

@Component
public class BowlingGameFactory {

    public BowlingGame create() {
        return new BowlingGame();
    }
}
