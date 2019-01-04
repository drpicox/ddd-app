Feature: DDD Bowling Game

  Bowling is a game in which a player
  rolls a ball into a lane trying
  to knock down the maximum number
  of pins.

  Scenario: Gutter game
    Given a new gutter Bowling Game
    When gutter rolls 20 times 0 pins
    Then the score of the gutter game is 0

  Scenario: All ones
    Given a new allOnes Bowling Game
    When allOnes rolls 20 times 1 pins
    Then the score of the allOnes game is 20


#  Scenario: Register two products
#    Given item patata with price 20 in money
#    Given item pastanaga with price 15 in money
#    And cash register 1
#
#    When record item patata at cash register 1
#    And record item pastanaga at cash register 1
#    And end cash register 1 item recording
#    And total is cash register 1 total
#    And record 50 as delivered money at cash register 1
#    And change is cash register 1 change
#
#    Then total is 35
#    And change is 15
#    And cash register 1 is ready to record a new item