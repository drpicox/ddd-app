package com.drpicox.dddapp;

import com.drpicox.dddapp.carSharing.CarSharingController;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class SpringCucumberStepDefs {

    private CarSharingController carSharingController;
    private NamedObjectsRegistry namedObjectsRegistry;

    public SpringCucumberStepDefs(CarSharingController carSharingController, NamedObjectsRegistry namedObjectsRegistry) {
        this.carSharingController = carSharingController;
        this.namedObjectsRegistry = namedObjectsRegistry;
    }

    @Before
    @After
    public void cleanup() {
        carSharingController.clear();
        namedObjectsRegistry.clear();
    }
}
