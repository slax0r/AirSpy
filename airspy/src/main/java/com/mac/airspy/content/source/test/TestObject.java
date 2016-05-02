package com.mac.airspy.content.source.test;

import com.mac.airspy.BaseARObject;
import com.mac.airspy.location.SimpleLocation;


public class TestObject extends BaseARObject {

    public TestObject(String id, SimpleLocation location) {
        super(id);

        this.location = location;
    }

    @Override
    public String getName() {
        return id;
    }


}
