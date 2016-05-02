package com.mac.airspy.content;

import com.mac.airspy.ARObject;

import java.io.IOException;
import java.util.List;

public interface ObjectSource {
    List<? extends ARObject> getObjects() throws IOException;

    ObjectViewProvider getInfoViewProvider();

    ObjectViewProvider getDetailsViewProvider();
}
