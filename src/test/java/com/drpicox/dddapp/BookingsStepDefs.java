package com.drpicox.dddapp;

import com.drpicox.dddapp.carSharing.Booking;
import com.drpicox.dddapp.carSharing.Car;
import com.drpicox.dddapp.carSharing.CarSharingController;
import com.drpicox.dddapp.carSharing.Member;
import cucumber.api.Format;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


public class BookingsStepDefs {

    private CarSharingController carSharingController;
    private NamedObjectsRegistry namedObjectsRegistry;

    public BookingsStepDefs(CarSharingController carSharingController, NamedObjectsRegistry namedObjectsRegistry) {
        this.carSharingController = carSharingController;
        this.namedObjectsRegistry = namedObjectsRegistry;
    }

    @When("^(\\w+) books the (\\w+) car for today from (\\d+:\\d+) to (\\d+:\\d+) h$")
    public void XXX_books_the_XXX_car_for_today_from_XXX_to_XXX_h(String memberName, String carName, @Format("HH:mm") Date startDate, @Format("HH:mm") Date endDate) {
        Member member = namedObjectsRegistry.recall(memberName);
        Car car = namedObjectsRegistry.recall(carName);

        carSharingController.bookCar(car, member, startDate, endDate);
    }

    @When("^(\\w+) (?:tries to extend|extends) the (\\w+) car booking for today from (\\d+:\\d+) until (\\d+:\\d+) h$")
    public void XXX_extends_the_XXX_car_booking_for_today_from_XXX_until_XXX_h(String memberName, String carName, @Format("HH:mm") Date startDate, @Format("HH:mm") Date endDate) {
        Car car = namedObjectsRegistry.recall(carName);
        Member member = namedObjectsRegistry.recall(memberName);
        carSharingController.extendBooking(car, member, startDate, endDate);
    }

    @When("^(\\w+) has picked up the (\\w+) car today at (\\d+:\\d+) h$")
    public void XXX_has_picked_up_the_XXX_car_today_at_XXX_h(String memberName, String carName, @Format("HH:mm") Date effectiveStartDate) {
        Car car = namedObjectsRegistry.recall(carName);
        Member member = namedObjectsRegistry.recall(memberName);
        carSharingController.pickupCar(car, member, effectiveStartDate);
    }

    @When("^(\\w+) (?:tries to finalize|finalizes) the (\\w+) car booking today at (\\d+:\\d+) h$")
    public void XXX_finalizes_the_XXX_car_booking_today_at_XXX_h(String memberName, String carName, @Format("HH:mm") Date effectiveEndDate) {
        Car car = namedObjectsRegistry.recall(carName);
        Member member = namedObjectsRegistry.recall(memberName);
        carSharingController.finalizeBooking(car, member, effectiveEndDate);
    }

    @Then("^(\\w+) has one booking$")
    public void XXX_has_one_booking(String memberName) {
        XXX_has_XXX_bookings(memberName, 1);
    }

    @Then("^(\\w+) has (\\d+) bookings$")
    public void XXX_has_XXX_bookings(String memberName, int bookingsCount) {
        Member member = namedObjectsRegistry.recall(memberName);
        List<Booking> bookings = carSharingController.listNotFinalizedsBookingsOf(member);

        System.out.println(bookings);
        assertEquals(bookingsCount, bookings.size());
    }

    @Then("^(\\w+) has booked the (\\w+) car for today from (\\d+:\\d+) to (\\d+:\\d+) h$")
    public void XXX_has_booked_the_XXX_car_from_XXX_to_XXX_h(String memberName, String carName, @Format("HH:mm") Date startDate, @Format("HH:mm") Date endDate) {
        Car car = namedObjectsRegistry.recall(carName);
        Member member = namedObjectsRegistry.recall(memberName);
        List<Booking> bookings = carSharingController.listNotFinalizedsBookingsOf(member);

        boolean found = bookings.stream().anyMatch(b -> b.isFor(car) && b.isBooking(startDate) && b.isEndingAt(endDate));
        assertTrue(found);
    }

    @Then("^(\\w+) has (\\d+) finalized bookings$")
    public void XXX_has_XXX_finalized_bookings(String memberName, int bookingsCount) {
        Member member = namedObjectsRegistry.recall(memberName);
        List<Booking> finalizedBookings = carSharingController.listFinalizedBookingsOf(member);

        System.out.println(finalizedBookings);
        assertEquals(bookingsCount, finalizedBookings.size());
    }

    @Then("^(\\w+) has picked up the (\\w+) car at (\\d+:\\d+) and returned it at (\\d+:\\d+) h$")
    public void XXX_has_piked_up_the_XXX_car_at_XXX_and_returned_it_at_XXX_h(String memberName, String carName, @Format("HH:mm") Date effectiveStartDate, @Format("HH:mm") Date effectiveEndDate) {
        Car car = namedObjectsRegistry.recall(carName);
        Member member = namedObjectsRegistry.recall(memberName);
        List<Booking> finalizedBookings = carSharingController.listFinalizedBookingsOf(member);

        boolean found = finalizedBookings.stream().anyMatch(b -> b.isFor(car) && b.isPickedUpAt(effectiveStartDate) && b.isReturnedAt(effectiveEndDate));
        assertTrue(found);
    }


}
