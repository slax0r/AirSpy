package com.mac.airspy.parameters;

public class CameraParameters {
    //values depending on display orientation
    public final double horizontalViewAngle;
    public final double verticalViewAngle;

    public CameraParameters(double horizontalViewAngle, double verticalViewAngle) {
        this.horizontalViewAngle = horizontalViewAngle;
        this.verticalViewAngle = verticalViewAngle;
    }
}