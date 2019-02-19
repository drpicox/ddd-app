package com.drpicox.dddapp.carSharing;


public class CarDictionary {

    public Car create(Zone zone) {
        Car car = new Car(zone);

        return car;
    }

    public void locateAt(Car car, Zone zone) {
        car.locateAt(zone);
    }
}
