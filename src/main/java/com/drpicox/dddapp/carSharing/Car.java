package com.drpicox.dddapp.carSharing;

public class Car {

    private Zone location;

    public Car(Zone zone) {
        this.location = zone;
    }

    public Zone getLocation() {
        return location;
    }

    public void locateAt(Zone location) {
        this.location = location;
    }
}
