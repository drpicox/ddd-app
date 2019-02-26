#Language: es
Feature: DDD Car Sharing

  Car sharing is a rent a car service based
  in rent by hours and membership.

  UL:
  Booking
  Car
  EffectiveEndDate
  EffectiveStartDate
  EndDate
  Finalize
  Location
  Member
  StartDate
  Zone

  Background:
    Given the TecnoCampus zone of Mataro at <41,2>
    Given the Glories zone of Barcelona at <39,4>

  Scenario: Peter books the Ranganito car
    Given the member Peter with the TecnoCampus zone
    And the car Ranganito at the TecnoCampus zone and a range of 270 Km

    When Peter books the Ranganito car for today from 13:00 to 17:00 h

    Then Peter has one booking
    And Peter has booked the Ranganito car for today from 13:00 to 17:00 h


  Scenario: Peter extends the booking of the Ranganito car
    Given the member Peter with the TecnoCampus zone
    And the member Homer with the TecnoCampus zone
    And the car Ranganito at the TecnoCampus zone and a range of 270 Km
    And Peter books the Ranganito car for today from 13:00 to 17:00 h
    And Homer books the Ranganito car for today from 18:00 to 19:00 h

    When Peter extends the Ranganito car booking for today from 13:00 until 18:00 h

    Then Peter has one booking
    And Peter has booked the Ranganito car for today from 13:00 to 18:00 h
    And Homer has 1 bookings
    And Homer has booked the Ranganito car for today from 18:00 to 19:00 h


  Scenario: Peter tries to extend the booking of the Ranganito car but it is already booked
    Given the member Peter with the TecnoCampus zone
    And the member Homer with the TecnoCampus zone
    And the car Ranganito at the TecnoCampus zone and a range of 270 Km
    And Peter books the Ranganito car for today from 13:00 to 17:00 h
    And Homer books the Ranganito car for today from 17:00 to 19:00 h

    When Peter tries to extend the Ranganito car booking for today from 13:00 until 18:00 h

    Then Peter has one booking
    And Peter has booked the Ranganito car for today from 13:00 to 17:00 h
    And Homer has one booking
    And Homer has booked the Ranganito car for today from 17:00 to 19:00 h


  Scenario: Peter returns the car and makes finalizes his booking
    Given the member Peter with the TecnoCampus zone
    And the car Ranganito at the TecnoCampus zone and a range of 270 Km
    And Peter books the Ranganito car for today from 13:00 to 17:00 h
    And Peter has picked up the Ranganito car today at 13:13 h
    And the Ranganito car was driven to the Glories zone

    When the Ranganito car is returned to the TecnoCampus zone
    And Peter finalizes the Ranganito car booking today at 16:54 h

    Then Peter has 0 bookings
    And Peter has 1 finalized bookings
    And Peter has picked up the Ranganito car at 13:13 and returned it at 16:54 h
    And the Ranganito car is located at the TecnoCampus zone


  Scenario: Peter cannot return the car because the car is not located at its zone
    Given the member Peter with the TecnoCampus zone
    And the car Ranganito at the TecnoCampus zone and a range of 270 Km
    And Peter books the Ranganito car for today from 13:00 to 17:00 h
    And Peter has picked up the Ranganito car today at 13:13 h
    And the Ranganito car was driven to the Glories zone

    When Peter tries to finalize the Ranganito car booking today at 16:54 h

    Then Peter has 1 bookings
    And Peter has 0 finalized bookings
    And Peter has booked the Ranganito car for today from 13:00 to 17:00 h
    And the Ranganito car is located at the Glories zone
