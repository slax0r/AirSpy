package com.mac.airspy.content.source.fr24;

import com.google.inject.Inject;
import com.mac.airspy.ARObject;
import com.mac.airspy.content.ObjectInfoViewProvider;
import com.mac.airspy.content.ObjectSource;

import java.io.IOException;
import java.util.List;

/**
 * Created by Maciej on 2014-10-18.
 */
public class FRObjectSource implements ObjectSource {

    @Inject
    private ZoneBoundsResolver zoneBoundsResolver;

    @Inject
    private TrafficProcessor trafficProcessor;

    @Inject
    private FRInfoViewProvider infoViewProvider;

    @Override
    public List<? extends ARObject> getObjects() throws IOException {

        Double[] bounds = zoneBoundsResolver.getBounds();

        return trafficProcessor.getPlanes(900, bounds);
    }

    @Override
    public ObjectInfoViewProvider getInfoViewProvider() {
        return infoViewProvider;
    }
}
