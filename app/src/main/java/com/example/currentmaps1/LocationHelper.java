package com.example.currentmaps1;

public class LocationHelper {

    private double Latitude;
    private double Longtitude;

    public LocationHelper(double latitude, double longtitude) {
        Latitude = latitude;
        Longtitude = longtitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongtitude() {
        return Longtitude;
    }

    public void setLongtitude(double longtitude) {
        Longtitude = longtitude;
    }
}
