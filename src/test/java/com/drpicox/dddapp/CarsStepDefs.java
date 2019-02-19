package com.drpicox.dddapp;

import com.drpicox.dddapp.carSharing.Car;
import com.drpicox.dddapp.carSharing.CarSharingController;
import com.drpicox.dddapp.carSharing.Zone;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;

public class CarsStepDefs {

    private CarSharingController carSharingController;
    private NamedObjectsRegistry namedObjectsRegistry;

    public CarsStepDefs(CarSharingController carSharingController, NamedObjectsRegistry namedObjectsRegistry) {
        this.carSharingController = carSharingController;
        this.namedObjectsRegistry = namedObjectsRegistry;
    }

    @Given("^the car (\\w+) at the (\\w+) zone and a range of (\\d+) Km$")
    public void the_car_XXX_at_the_XXX_zone_and_range_of_XXX_Km(String carName, String zoneName, long range) {
        Zone zone = namedObjectsRegistry.recall(zoneName);
        Car car = carSharingController.createCar(zone);

        namedObjectsRegistry.register(carName, car);
    }

    @Given("^the (\\w+) car was driven to the (\\w+) zone$")
    public void the_XXX_car_was_driven_to_the_XXX_zone(String carName, String zoneName) {
        Zone zone = namedObjectsRegistry.recall(zoneName);
        Car car = namedObjectsRegistry.recall(carName);

        carSharingController.locateCarAt(car, zone);
    }


    @Given("^the (\\w+) car is returned to the (\\w+) zone$")
    public void the_XXX_car_is_returned_to_the_XXX_zone(String carName, String zoneName) {
        Zone zone = namedObjectsRegistry.recall(zoneName);
        Car car = namedObjectsRegistry.recall(carName);

        carSharingController.locateCarAt(car, zone);
    }

    @Then("^the (\\w+) car is located at the (\\w+) zone$")
    public void the_XXX_car_is_located_at_the_XXX_zone(String carName, String zoneName) {
        Zone zone = namedObjectsRegistry.recall(zoneName);
        Car car = namedObjectsRegistry.recall(carName);

        assertEquals(zone, car.getLocation());
    }

}
