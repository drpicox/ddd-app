package com.drpicox.dddapp.carSharing;

public class Car {

    private Zone zone;
    private Zone location;

    public Car(Zone zone) {
        this.zone = zone;
        this.location = zone;
    }

    public Zone getLocation() {
        return location;
    }

    public boolean isLocatedAtZone() {
        return zone.equals(location);
    }

    public void locateAt(Zone location) {
        this.location = location;
    }
}
