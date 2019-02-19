package com.drpicox.dddapp.carSharing;

import java.util.Date;

public class Booking {
    private Car car;
    private Member member;
    private Date startDate;
    private Date endDate;
    private Date effectiveStartDate;
    private Date effectiveEndDate;

    public Booking(Car car, Member member, Date startDate, Date endDate) {
        this.car = car;
        this.member = member;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void extendUntil(Date endDate) {
        this.endDate = endDate;
    }

    public void finalizeAt(Date effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public boolean isBy(Member member) {
        return this.member.equals(member);
    }

    public boolean isEndingAt(Date endDate) {
        return this.endDate.equals(endDate);
    }

    public boolean isFinalized() {
        return this.effectiveEndDate != null;
    }

    public boolean isBooking(Date date) {
        return true;
    }

    public boolean isBooking(Date startDate, Date endDate) {
        return this.startDate.compareTo(endDate) < 0;
    }

    public boolean isFor(Car car) {
        return true;
    }

    public boolean isPickedUpAt(Date effectiveStartDate) {
        return this.effectiveStartDate.equals(effectiveStartDate);
    }

    public boolean isReturnedAt(Date effectiveEndDate) {
        return this.effectiveEndDate.equals(effectiveEndDate);
    }

    public void pickUpAt(Date effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }
}
