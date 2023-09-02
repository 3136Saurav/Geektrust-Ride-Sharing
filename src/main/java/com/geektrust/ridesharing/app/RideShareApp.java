package com.geektrust.ridesharing.app;

import com.geektrust.ridesharing.model.Driver;
import com.geektrust.ridesharing.model.Ride;
import com.geektrust.ridesharing.model.Rider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideShareApp {
    Map<String, Ride> rideMap = new HashMap<>();
    Map<String, Driver> driverMap = new HashMap<>();
    Map<String, Rider> riderMap = new HashMap<>();

    public void setDriverMap(Map<String, Driver> driverMap) {
        this.driverMap = driverMap;
    }

    public void setRiderMap(Map<String, Rider> riderMap) {
        this.riderMap = riderMap;
    }

    public Map<String, List<Driver>> getDriversMappedToRiderMap() {
        return driversMappedToRiderMap;
    }

    public void setDriversMappedToRiderMap(Map<String, List<Driver>> driversMappedToRiderMap) {
        this.driversMappedToRiderMap = driversMappedToRiderMap;
    }

    Map<String, List<Driver>> driversMappedToRiderMap = new HashMap<>();

    public void addDriver(Driver driver) {
        driverMap.put(driver.getDriverId(), driver);
    }

    public void addRider(Rider rider) {
        riderMap.put(rider.getRiderId(), rider);
    }

    public void setRideMap(Map<String, Ride> rideMap) {
        this.rideMap = rideMap;
    }

    public Map<String, Ride> getRideMap() {
        return rideMap;
    }

    public Map<String, Driver> getDriverMap() {
        return driverMap;
    }

    public Map<String, Rider> getRiderMap() {
        return riderMap;
    }

    public Rider getRider(String riderId) {
        return riderMap.get(riderId);
    }

    public void mapDriversToRider(String riderId, List<Driver> driverList) {
        driversMappedToRiderMap.put(riderId, driverList);
    }

    public void addRide(Ride ride) {
        rideMap.put(ride.getRideId(), ride);
    }
}
