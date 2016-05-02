package com.mac.airspy;


public interface ApplicationComponent {
    String getIdentifier();

    ComponentState getState();

    void setStateListener(StateChangedListener stateListener);

    interface StateChangedListener {
        void onStateChanged(ApplicationComponent component, ComponentState newState);
    }
}
