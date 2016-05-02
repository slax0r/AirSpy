package com.mac.airspy;

import android.graphics.PointF;

import com.google.inject.Inject;

import java.util.LinkedList;
import java.util.List;

import roboguice.inject.ContextSingleton;

@ContextSingleton
public class VisibleObjectsObtainer {
    @Inject
    private ScreenPositionCalculator screenPositionCalculator;

    @Inject
    private ObjectsProvider objectsProvider;

    private List<ObjectOnScreen> objectsOnScreen;

    public void update() {
        List<ObjectOnScreen> objectsOnScreen = new LinkedList<ObjectOnScreen>();
        List<? extends ARObject> allObjects = objectsProvider.getObjectsInRange();

        for (ARObject object : allObjects) {
            PointF screenPos = screenPositionCalculator.calculateScreenPosition(object);
            if (screenPos == null)
                continue;

            objectsOnScreen.add(new ObjectOnScreen(object, screenPos));
        }

        this.objectsOnScreen = objectsOnScreen;
    }

    public List<ObjectOnScreen> getObjectsOnScreen() {
        return objectsOnScreen;
    }
}
