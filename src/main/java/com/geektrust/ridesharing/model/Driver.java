package com.geektrust.ridesharing.model;

import java.util.Objects;

public class Driver {
    private String driverId;
    private Location location;
    private  boolean isAvailable;

    public Driver() {
        this.isAvailable = true;
    }

    public Driver(String driverId, Location location) {
        this.driverId = driverId;
        this.location = location;
        this.isAvailable = true;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(driverId, driver.driverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverId);
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
