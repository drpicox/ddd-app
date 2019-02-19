package com.drpicox.dddapp;

import com.drpicox.dddapp.carSharing.CarSharingController;
import com.drpicox.dddapp.carSharing.Zone;
import cucumber.api.java.en.Given;

public class ZonesStepDefs {

    private CarSharingController carSharingController;
    private NamedObjectsRegistry namedObjectsRegistry;

    public ZonesStepDefs(CarSharingController carSharingController, NamedObjectsRegistry namedObjectsRegistry) {
        this.carSharingController = carSharingController;
        this.namedObjectsRegistry = namedObjectsRegistry;
    }

    @Given("^the (\\w+) zone of (\\w+) at <(\\d+),(\\d+)>$")
    public void la_zona_WWW_de_(String name, String city, int latitude, int longitude) {
        Zone zone = carSharingController.createZone();
        namedObjectsRegistry.register(name, zone);
    }

}
