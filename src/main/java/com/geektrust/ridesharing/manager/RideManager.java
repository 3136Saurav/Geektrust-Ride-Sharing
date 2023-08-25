package com.geektrust.ridesharing.manager;

import com.geektrust.ridesharing.constants.CommonConstants;
import com.geektrust.ridesharing.pojo.*;
import com.geektrust.ridesharing.util.DistanceCalculator;

import java.util.*;

public class RideManager {
    DriverManager driverManager;
    RiderManager riderManager;
    BillManager billManager;
    DistanceCalculator distanceCalculator;

    Map<String, Ride> rideMap = new HashMap<>();
    Map<String, Bill> rideBillMap = new HashMap<>();
    Map<String, List<Driver>> riderToDriversMap = new HashMap<>();

    public RideManager(DriverManager driverManager, RiderManager riderManager, BillManager billManager, DistanceCalculator distanceCalculator) {
        this.driverManager = driverManager;
        this.riderManager = riderManager;
        this.billManager = billManager;
        this.distanceCalculator = distanceCalculator;
    }

    public Ride startRide(String rideId, String riderId, String driverId) {
        if (rideMap.containsKey(rideId)) {
            return null;
        }

        Rider rider = riderManager.getRiderMap().get(riderId);
        Ride ride = new Ride();
        ride.setRideId(rideId);
        ride.setDriverId(driverId);
        ride.setRiderId(riderId);
        ride.setSource(rider.getLocation());

        rideMap.put(rideId, ride);
        return ride;
    }

    public Bill stopRide(String rideId, Location destinationLocation, long timeTaken) {
        if (!rideMap.containsKey(rideId)) {
            return null;
        }

        Ride ride = rideMap.get(rideId);
        ride.setDestination(destinationLocation);
        ride.setTimeTaken(timeTaken);

        Bill bill = billManager.createBill(ride);
        rideBillMap.put(rideId, bill);
        return bill;
    }

    public List<Driver> matchRide(String riderId) {
        if (!riderManager.getRiderMap().containsKey(riderId)) {
            return Collections.emptyList();
        }
        Rider rider = riderManager.getRiderMap().get(riderId);
        List<Driver> driverList = getClosestDrivers(rider);
        return driverList;
    }

    private List<Driver> getClosestDrivers(Rider rider) {
        PriorityQueue<DriverRider> driverRiderPQ = new PriorityQueue<>((a, b) -> {
            if (a.getDistanceBetweenRiderAndDriver() == b.getDistanceBetweenRiderAndDriver()) {
                return a.getDriver().getDriverId().compareTo(b.getDriver().getDriverId());
            } else {
                return Double.compare(a.getDistanceBetweenRiderAndDriver(), b.getDistanceBetweenRiderAndDriver());
            }
        });
        for (Map.Entry<String, Driver> entry: driverManager.getDriverMap().entrySet()) {
            Driver driver = entry.getValue();
            double distance = distanceCalculator.calculateDistance(driver.getLocation(), rider.getLocation());
            if (distance > CommonConstants.NEAREST_RANGE) {
                continue;
            }
            DriverRider driverRider = new DriverRider(distance, driver, rider);
            driverRiderPQ.offer(driverRider);
        }

        List<Driver> driverList = new ArrayList<>();
        while (!driverRiderPQ.isEmpty()) {
            driverList.add(driverRiderPQ.poll().getDriver());
        }
        return driverList;
    }

    public Map<String, Ride> getRideMap() {
        return rideMap;
    }

    public Map<String, List<Driver>> getRiderToDriversMap() {
        return riderToDriversMap;
    }

    public Map<String, Bill> getRideBillMap() {
        return rideBillMap;
    }

    public Bill getBill(String rideId) {
        return rideBillMap.get(rideId);
    }
}
