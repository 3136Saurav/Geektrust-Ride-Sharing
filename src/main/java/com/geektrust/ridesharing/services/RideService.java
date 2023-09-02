package com.geektrust.ridesharing.services;

import com.geektrust.ridesharing.app.RideShareApp;
import com.geektrust.ridesharing.constants.CommonConstants;
import com.geektrust.ridesharing.enums.RideStatus;
import com.geektrust.ridesharing.exceptions.InvalidRideException;
import com.geektrust.ridesharing.exceptions.RideNotCompletedException;
import com.geektrust.ridesharing.model.*;
import com.geektrust.ridesharing.util.DistanceCalculator;

import java.util.ArrayList;
import java.util.List;

public class RideService {
    DriverService driverService;
    RideShareApp rideShareApp;
    DistanceCalculator distanceCalculator;
    RiderService riderService;

    public RideService(RideShareApp rideShareApp, DriverService driverService, DistanceCalculator distanceCalculator, RiderService riderService) {
        this.rideShareApp = rideShareApp;
        this.driverService = driverService;
        this.distanceCalculator = distanceCalculator;
        this.riderService = riderService;
    }
    public void matchRider(String riderId) throws InvalidRideException {

        Rider rider = rideShareApp.getRider(riderId);

        if (rider == null || rider.isActive()) {
            throw new InvalidRideException("INVALID_RIDE");
        }

        List<Driver> availableDriverList = driverService.getAllAvailableDrivers();
        if (availableDriverList == null || availableDriverList.isEmpty()) {
            System.out.println("NO_DRIVERS_AVAILABLE");
            return;
        }

        List<DriverRider> driverRiderList = new ArrayList<>();
        for (Driver driver : availableDriverList) {
            double distance = distanceCalculator.calculateDistance(rider.getLocation(), driver.getLocation());
            DriverRider driverRider = new DriverRider(distance, driver, rider);
            driverRiderList.add(driverRider);
        }

        driverRiderList.sort((a, b) -> {
            if (a.getDistanceBetweenRiderAndDriver() == b.getDistanceBetweenRiderAndDriver()) {
                return a.getDriver().getDriverId().compareTo(b.getDriver().getDriverId());
            } else {
                return Double.compare(a.getDistanceBetweenRiderAndDriver(), b.getDistanceBetweenRiderAndDriver());
            }
        });

        List<Driver> driverList = new ArrayList<>();
        for (int i=0; i < Math.min(driverRiderList.size(), CommonConstants.FIVE); i++) {
            if (driverRiderList.get(i).getDistanceBetweenRiderAndDriver() > CommonConstants.NEAREST_RANGE) continue;
            driverList.add(driverRiderList.get(i).getDriver());
        }

        rideShareApp.mapDriversToRider(riderId, driverList);

        if (driverList.isEmpty()) {
            System.out.println("NO_DRIVERS_AVAILABLE");
        } else {
            System.out.print("DRIVERS_MATCHED ");
            for (int i=0; i < driverList.size(); i++) {
                Driver driver = driverList.get(i);
                System.out.print(driver.getDriverId());
                if (i != driverList.size() - 1) System.out.print(" ");
            }
            System.out.println();
        }
//        return true;
    }


    public boolean isRideExists(String rideId) {
        return rideShareApp.getRideMap().containsKey(rideId);
    }

    public void startRide(String rideId, int driverNumber, String riderId) throws InvalidRideException {
        if (isRideExists(rideId) || isRideInProgress(rideId) || !riderService.isRiderExists(riderId) || riderService.isRiderActive(riderId)) {
            throw new InvalidRideException("INVALID_RIDE");
        }

        List<Driver> driverList = rideShareApp.getDriversMappedToRiderMap().get(riderId);
        if (driverList == null || driverList.isEmpty() || driverNumber < CommonConstants.ONE || driverNumber > CommonConstants.FIVE) {
            throw new InvalidRideException("INVALID_RIDE");
        }

        Driver driver = driverList.get(driverNumber - 1);

        if (!driver.isAvailable()) {
            throw new InvalidRideException("INVALID_RIDE");
        }

        Rider rider = riderService.getRider(riderId);
        Ride ride = new Ride();

        rider.setActive(true);
        driver.setAvailable(false);
        ride.setRideId(rideId);
        ride.setRider(rider);
        ride.setDriver(driver);
        ride.setSource(rider.getLocation());
        ride.setRideStatus(RideStatus.RIDE_STARTED);

        rideShareApp.addRide(ride);

        System.out.println("RIDE_STARTED " + rideId);
    }

    public boolean isRideInProgress(String rideId) {
        Ride ride = rideShareApp.getRideMap().get(rideId);
        return ride != null &&  RideStatus.RIDE_STARTED.equals(ride.getRideStatus());
    }

    public boolean isRideCompleted(String rideId) {
        Ride ride = rideShareApp.getRideMap().get(rideId);
        return ride != null &&  RideStatus.RIDE_STOPPED.equals(ride.getRideStatus());
    }

    public void stopRide(String rideId, int destinationX, int destinationY, int timeTaken) throws InvalidRideException, RideNotCompletedException {
        if (!isRideExists(rideId) || isRideCompleted(rideId)) {
            throw new InvalidRideException("INVALID_RIDE");
        }

        if (!isRideInProgress(rideId)) {
            throw new RideNotCompletedException("RIDE_NOT_COMPLETED");
        }

        Ride ride = getRide(rideId);
        Location destinationLocation = new Location(destinationX, destinationY);
        ride.setDestination(destinationLocation);
        ride.setTimeTaken(timeTaken);
        ride.setRideStatus(RideStatus.RIDE_STOPPED);

        System.out.println("RIDE_STOPPED " + rideId);
    }

    public Ride getRide(String rideId) {
        Ride ride = rideShareApp.getRideMap().get(rideId);
        return ride;
    }
}
