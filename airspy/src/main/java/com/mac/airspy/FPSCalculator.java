package com.mac.airspy;

public class FPSCalculator {
    private int fps = 0;
    private int frameCounter = 0;
    private long lastUpdate;

    public int getFpsAndUpdate() {
        long time = System.currentTimeMillis();

        frameCounter++;

        if (lastUpdate < time - 1000) {
            lastUpdate = System.currentTimeMillis();
            fps = frameCounter;
            frameCounter = 0;
        }

        return fps;
    }
}
