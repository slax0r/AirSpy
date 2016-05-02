package com.mac.airspy;

import android.content.SharedPreferences;

import com.google.inject.Inject;


public class UserPreferencesHelper {
    @Inject
    private SharedPreferences sharedPrefs;

    public int getRange() {
        return sharedPrefs.getInt("range", ObjectsProvider.RANGE_DEFAULT_KM);
    }

    public void setRange(int range) {
        sharedPrefs.edit()
                .putInt("range", range)
                .apply();
    }

    public boolean isRadarVisible() {
        return sharedPrefs.getBoolean("radarVisible", true);
    }

    public void setRadarVisible(boolean radarVisible) {
        sharedPrefs.edit()
                .putBoolean("radarVisible", radarVisible)
                .apply();
    }
}
