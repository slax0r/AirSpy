package com.mac.airspy;

import com.mac.airspy.location.SimpleLocation;
import com.mac.airspy.utils.Vector3D;


public interface ARObject {
    Object getId();

    String getName();

    Vector3D getApproximatedDistanceVector();

    void setApproximatedDistanceVector(Vector3D approximatedDistanceVector);

    SimpleLocation getLocation();
}
