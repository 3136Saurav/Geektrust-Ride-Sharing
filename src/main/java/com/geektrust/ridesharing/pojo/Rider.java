package com.geektrust.ridesharing.pojo;

import java.util.Objects;

public class Rider {
    private String riderId;
    private Location location;

    public Rider() {
    }

    public Rider(String riderId, Location location) {
        this.riderId = riderId;
        this.location = location;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
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
        Rider rider = (Rider) o;
        return Objects.equals(riderId, rider.riderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(riderId);
    }

}
