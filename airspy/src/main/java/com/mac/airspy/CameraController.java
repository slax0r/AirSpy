package com.mac.airspy;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.Camera;
import android.widget.RelativeLayout;

import com.google.inject.Inject;
import com.mac.airspy.parameters.CameraParameters;

import roboguice.inject.ContextSingleton;
import roboguice.inject.InjectView;


@ContextSingleton
public class CameraController extends BaseApplicationComponent implements CameraPreview.SurfaceChangedListener {

    @InjectView(R.id.cameraPreview)
    private RelativeLayout cameraPreviewContainer;

    @Inject
    private Resources resources;

    private CameraParameters cameraParameters;
    private CameraPreview cameraPreview;

    public void resume() {
        cameraPreview = new CameraPreview((Activity) ctx, 0, CameraPreview.LayoutMode.FitToParent, this);
        cameraPreview.setLayoutParams(
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT)
        );


        cameraPreviewContainer.removeAllViews();
        cameraPreviewContainer.addView(cameraPreview);
    }

    public void pause() {
        setState(ComponentState.STOPPED);

        cameraPreview.stop();
        cameraPreviewContainer.removeAllViews(); // This is necessary.
        cameraPreview = null;
    }


    public CameraParameters getCameraParameters() {
        return cameraParameters;
    }

    @Override
    public void onSurfaceChanged(Camera.Parameters parameters) {

        double cameraHorizontalAngle = Math.toRadians(parameters.getHorizontalViewAngle());
        double cameraVerticalAngle = Math.toRadians(parameters.getVerticalViewAngle());

        if (Configuration.ORIENTATION_LANDSCAPE == resources.getConfiguration().orientation) {
            this.cameraParameters = new CameraParameters(cameraHorizontalAngle, cameraVerticalAngle);
        }else {
            this.cameraParameters = new CameraParameters(cameraVerticalAngle, cameraHorizontalAngle);
        }

        setState(ComponentState.READY);
    }
}
