package com.mac.airspy;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.google.inject.Inject;

import roboguice.inject.ContextSingleton;


@ContextSingleton
public class OrientationService extends BaseApplicationComponent implements SensorEventListener{

    private final SensorManager sensorManager;

    private final Sensor rotationSensor;

    private float[] sensorValues = new float[4];

    private float[] rotationMatrix = new float[16];
    private float[] remapedRotationMatrix = new float[16];
    private float[] orientationValues = new float[3];

    @Inject
    public OrientationService(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
        this.rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        if (rotationSensor == null) {
            setState(ComponentState.ERROR);
        }
    }

    public void start() {
        if (state != ComponentState.ERROR) {
            sensorManager.registerListener(this, rotationSensor, SensorManager.SENSOR_DELAY_GAME);
            state = ComponentState.STARTING;
        }
    }

    public void stop() {
        sensorManager.unregisterListener(this);

        setState(ComponentState.STOPPED);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        sensorValues = event.values;

        setState(ComponentState.READY);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public float[] getOrientation() {
        SensorManager.getRotationMatrixFromVector(rotationMatrix, sensorValues);

        SensorManager.remapCoordinateSystem(rotationMatrix,
                SensorManager.AXIS_X, SensorManager.AXIS_Z, remapedRotationMatrix);

        SensorManager.getOrientation(remapedRotationMatrix, orientationValues);

        orientationValues[1] = -orientationValues[1];

        return orientationValues;
    }
}
