package com.mac.airspy.content.source.fr24;

import com.mac.airspy.BaseARObject;
import com.mac.airspy.MovingARObject;

import org.apache.commons.lang3.StringUtils;


public class Plane extends BaseARObject implements MovingARObject{

    private String hex;
    private String callsign;
    private String flightNumber;
    private String aircraftCode;
    private String registration;
    private String fromCode;
    private String toCode;
    private double speedKmh;
    private double track;
    private long dataTimestamp;

    public Plane(String id) {
        super(id);
    }

    @Override
    public String getName() {
        if (!StringUtils.isBlank(callsign))
            return callsign;

        if (!StringUtils.isBlank(flightNumber))
            return flightNumber;

        return "UNKNOWN";
    }

    @Override
    public double getSpeedKmh() {
        return speedKmh;
    }

    public void setSpeedKmh(double speedKmh) {
        this.speedKmh = speedKmh;
    }

    @Override
    public double getTrack() {
        return track;
    }

    public void setTrack(double track) {
        this.track = track;
    }

    @Override
    public long getLastUpdateTime() {
        return dataTimestamp;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getFromCode() {
        return fromCode;
    }

    public void setFromCode(String fromCode) {
        this.fromCode = fromCode;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    public long getDataTimestamp() {
        return dataTimestamp;
    }

    public void setDataTimestamp(long dataTimestamp) {
        this.dataTimestamp = dataTimestamp;
    }
}
