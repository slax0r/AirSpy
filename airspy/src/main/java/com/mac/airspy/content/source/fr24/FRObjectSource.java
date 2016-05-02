package com.mac.airspy.content.source.fr24;

import com.google.inject.Inject;
import com.mac.airspy.ARObject;
import com.mac.airspy.content.ObjectSource;
import com.mac.airspy.content.ObjectViewProvider;

import java.io.IOException;
import java.util.List;


public class FRObjectSource implements ObjectSource {

    @Inject
    private ZoneBoundsResolver zoneBoundsResolver;

    @Inject
    private TrafficProcessor trafficProcessor;

    @Inject
    private FRInfoViewProvider infoViewProvider;

    @Inject
    private FRDetailsViewProvider detailsViewProvider;

    @Override
    public List<? extends ARObject> getObjects() throws IOException {

        Double[] bounds = zoneBoundsResolver.getBounds();

        return trafficProcessor.getPlanes(bounds);
    }

    @Override
    public ObjectViewProvider getInfoViewProvider() {
        return infoViewProvider;
    }

    @Override
    public ObjectViewProvider getDetailsViewProvider() {
        return detailsViewProvider;
    }
}
