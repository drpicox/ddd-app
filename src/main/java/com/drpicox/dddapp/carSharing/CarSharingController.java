package com.drpicox.dddapp.carSharing;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CarSharingController {

    private BookingDictionary bookingDictionary = new BookingDictionary();
    private CarDictionary carDictionary = new CarDictionary();
    private MemberDictionary memberDictionary = new MemberDictionary();
    private ZoneDictionary zoneDictionary = new ZoneDictionary();

    public void bookCar(Car car, Member member, Date startDate, Date endDate) {
        bookingDictionary.book(car, member, startDate, endDate);
    }

    public void clear() {
        bookingDictionary.clear();
    }

    public Car createCar(Zone zone) {
        return carDictionary.create(zone);
    }

    public Member createMember(String name, Zone zone) {
        return memberDictionary.create(name, zone);
    }

    public Zone createZone() {
        return zoneDictionary.create();
    }

    public void extendBooking(Car car, Member member, Date startDate, Date endDate) {
        bookingDictionary.extend(car, member, startDate, endDate);
    }

    public void finalizeBooking(Car car, Member member, Date effectiveEndDate) {
        bookingDictionary.finalize(car, member, effectiveEndDate);
    }

    public List<Booking> listNotFinalizedsBookingsOf(Member member) {
        return bookingDictionary.listNotFinalizedsOf(member);
    }

    public List<Booking> listFinalizedBookingsOf(Member member) {
        return bookingDictionary.listFinalizedOf(member);
    }

    public void locateCarAt(Car car, Zone zone) {
        carDictionary.locateAt(car, zone);
    }

    public void pickupCar(Car car, Member member, Date effectiveStartDate) {
        bookingDictionary.pickup(car, member, effectiveStartDate);
    }
}
