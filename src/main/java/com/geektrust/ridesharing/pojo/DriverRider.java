package com.geektrust.ridesharing.pojo;

public class DriverRider {
    private double distanceBetweenRiderAndDriver;
    private Driver driver;
    private Rider rider;

    public DriverRider(double distanceBetweenRiderAndDriver, Driver driver, Rider rider) {
        this.distanceBetweenRiderAndDriver = distanceBetweenRiderAndDriver;
        this.driver = driver;
        this.rider = rider;
    }

    public double getDistanceBetweenRiderAndDriver() {
        return distanceBetweenRiderAndDriver;
    }

    public void setDistanceBetweenRiderAndDriver(double distanceBetweenRiderAndDriver) {
        this.distanceBetweenRiderAndDriver = distanceBetweenRiderAndDriver;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }
}
