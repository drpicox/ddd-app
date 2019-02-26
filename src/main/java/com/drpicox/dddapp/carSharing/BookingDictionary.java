package com.drpicox.dddapp.carSharing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BookingDictionary {

    private List<Booking> bookings = new ArrayList<>();

    public void book(Car car, Member member, Date startDate, Date endDate) {
        Booking booking = new Booking(car, member, startDate, endDate);

        bookings.add(booking);
    }

    public void clear() {
        bookings.clear();
    }

    public void extend(Car car, Member member, Date startDate, Date endDate) {
        Booking booking = find(car, member, startDate);

        boolean isAlreadyBooked = bookings.stream().anyMatch(b -> b != booking && b.isBooking(startDate, endDate));

        if (!isAlreadyBooked) booking.extendUntil(endDate);
    }

    public void finalize(Car car, Member member, Date effectiveEndDate) {
        if (!car.isLocatedAtZone()) return;

        Booking booking = find(car, member, effectiveEndDate);

        booking.finalizeAt(effectiveEndDate);
    }

    public List<Booking> listFinalizedOf(Member member) {
        return listOf(member).stream().filter(b -> b.isFinalized()).collect(Collectors.toList());
    }

    public void pickup(Car car, Member member, Date effectiveStartDate) {
        Booking booking = find(car, member, effectiveStartDate);

        booking.pickUpAt(effectiveStartDate);
    }

    private Booking find(Car car, Member member, Date bookingDate) {
        return listOf(member).stream().findAny().get();
    }

    public List<Booking> listNotFinalizedsOf(Member member) {
        return listOf(member).stream().filter(b -> !b.isFinalized()).collect(Collectors.toList());
    }

    private List<Booking> listOf(Member member) {
        return bookings.stream().filter(b -> b.isBy(member)).collect(Collectors.toList());
    }


}
