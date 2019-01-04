package com.drpicox.dddapp;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class DDDBowlingStepDefs {

    @Autowired
    private BowlingGameFactory bowlingGameFactory;

    private Map<String, BowlingGame> games = new HashMap<>();

    @Given("a new (\\w+) Bowling Game")
    public void new_bowling_game(String gameName) {
        BowlingGame game = bowlingGameFactory.create();
        games.put(gameName, game);
    }

    @When("(\\w+) rolls (\\w+) times (\\w+) pins")
    public void rolls_many_pins(String gameName, int times, int pins) {
        BowlingGame game = games.get(gameName);

        for (int i = 0; i < times; i++) {
            game.roll(pins);
        }
    }

    @Then("the score of the (\\w+) game is (\\w+)")
    public void assert_score(String gameName, int score) {
        BowlingGame game = games.get(gameName);

        assertEquals(score, game.score());
    }
}
