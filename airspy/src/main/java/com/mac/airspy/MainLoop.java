package com.mac.airspy;

import com.google.inject.Inject;


public class MainLoop implements Runnable {
    private boolean running = true;

    @Inject
    private ARLayer arLayer;

    @Inject
    private VisibleObjectsObtainer visibleObjectsObtainer;

    @Inject
    private ObjectsProvider objectsProvider;

    @Inject
    private FPSCalculator fpsCalculator;

    @Inject
    private ObjectsPositionApproximator positionApproximator;

    @Override
    public void run() {
        while (running) {

            if (ComponentState.READY == objectsProvider.getState()) {
                arLayer.setFps(fpsCalculator.getFpsAndUpdate());

                positionApproximator.updateApproximatedPositions();

                visibleObjectsObtainer.update();
                arLayer.draw(visibleObjectsObtainer.getObjectsOnScreen());
            }

        }
    }

    public void stop() {
        running = false;
    }
}
