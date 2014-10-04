package com.mac.airspy.location;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.google.inject.Inject;
import roboguice.service.RoboService;

/**
 * Created by Maciej on 2014-10-02.
 */
public class BackgroundLocationService extends RoboService implements
        GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    private static final long UPDATE_INTERVAL_SECONDS = 10;
    private static final long FASTEST_INTERVAL_SECONDS = 1;

    @Inject
    private Context ctx;

    private LocationListener listener;

    private final LocalBinder binder = new LocalBinder();

    private LocationClient locationClient;
    private LocationRequest locationRequest;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("", "CREATE");

        if (!playServicesAvailable()) {
            Toast.makeText(ctx, "Google Play Services not available", Toast.LENGTH_SHORT).show();
            return;
        }

        locationClient = new LocationClient(this, this, this);

        locationClient.connect();

        buildLocationRequest();
    }

    private void buildLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(
                LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL_SECONDS);
        locationRequest.setFastestInterval(FASTEST_INTERVAL_SECONDS);
    }

    @Override
    public void onDestroy() {
        if (locationClient != null) {
            locationClient.disconnect();
        }

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private boolean playServicesAvailable() {
        return GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS;
    }

    @Override
    public void onConnected(Bundle bundle) {
        //TODO usunac
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();

        locationClient.requestLocationUpdates(locationRequest, this);
    }

    @Override
    public void onDisconnected() {
        //TODO usunac
        Toast.makeText(this, "Disconnected. Please re-connect.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        String msg = "Google Play Services error: " + result.getErrorCode();
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();

        retryConnecting();
    }

    @Override
    public void onLocationChanged(Location location) {
        notifyListener(location);
    }

    private void notifyListener(Location location) {
        if (listener != null) {
            listener.onLocationChanged(location);
        }
    }

    private void retryConnecting() {
        if (!locationClient.isConnecting()) {
            locationClient.connect();
        }
    }

    public void setListener(LocationListener listener) {
        this.listener = listener;

        sendLastLocationIfPresent();
    }

    private void sendLastLocationIfPresent() {
        if (locationClient != null && locationClient.isConnected()) {
            Location lastLocation = locationClient.getLastLocation();
            if (lastLocation != null) {
                notifyListener(lastLocation);
            }
        }
    }

    public class LocalBinder extends Binder{
        public BackgroundLocationService getServiceInstance() {
            return BackgroundLocationService.this;
        }
    }

    public static interface LocationListener {
        public void onLocationChanged(Location location);
    }
}
