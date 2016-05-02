package com.mac.airspy;


public interface MovingARObject extends ARObject {
    double getSpeedKmh();

    double getTrack();

    long getLastUpdateTime();
}
