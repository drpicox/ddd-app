package com.drpicox.dddapp;

import com.drpicox.dddapp.carSharing.CarSharingController;
import com.drpicox.dddapp.carSharing.Member;
import com.drpicox.dddapp.carSharing.Zone;
import cucumber.api.java.en.Given;
import org.springframework.boot.test.context.SpringBootTest;

public class MembersStepDefs {

    private CarSharingController carSharingController;
    private NamedObjectsRegistry namedObjectsRegistry;

    public MembersStepDefs(CarSharingController carSharingController, NamedObjectsRegistry namedObjectsRegistry) {
        this.carSharingController = carSharingController;
        this.namedObjectsRegistry = namedObjectsRegistry;
    }

    @Given("^the member (\\w+) with the (\\w+) zone$")
    public void lthe_member_WWW_with_the_WWW_zone(String memberName, String zoneName) {
        Zone zone = namedObjectsRegistry.recall(zoneName);
        Member member = carSharingController.createMember(memberName, zone);

        namedObjectsRegistry.register(memberName, member);
    }

}
