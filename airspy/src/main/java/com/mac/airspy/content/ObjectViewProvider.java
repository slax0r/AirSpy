package com.mac.airspy.content;

import android.view.View;

import com.mac.airspy.ARObject;

import java.io.IOException;


public interface ObjectViewProvider {
    View getView(ARObject object) throws IOException;
}
