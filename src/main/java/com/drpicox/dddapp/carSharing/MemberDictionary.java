package com.drpicox.dddapp.carSharing;

public class MemberDictionary {

    public Member create(String name, Zone zone) {
        Member member = new Member(name, zone);

        return member;
    }


}
