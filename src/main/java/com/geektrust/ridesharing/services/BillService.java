package com.geektrust.ridesharing.services;

import com.geektrust.ridesharing.exceptions.InvalidRideException;
import com.geektrust.ridesharing.exceptions.RideNotCompletedException;
import com.geektrust.ridesharing.model.Ride;
import com.geektrust.ridesharing.response.BillDto;
import com.geektrust.ridesharing.util.DistanceCalculator;
import com.geektrust.ridesharing.util.FareCalculator;

public class BillService {
    private RideService rideService;
    private FareCalculator fareCalculator;
    private DistanceCalculator distanceCalculator;

    public BillService(RideService rideService, FareCalculator fareCalculator, DistanceCalculator distanceCalculator) {
        this.rideService = rideService;
        this.fareCalculator = fareCalculator;
        this.distanceCalculator = distanceCalculator;
    }

    public BillDto generateBill(String rideId) throws RideNotCompletedException, InvalidRideException {
        if (!rideService.isRideExists(rideId)) {
            throw new InvalidRideException("INVALID_RIDE");
        }



        if (!rideService.isRideCompleted(rideId)) {
            throw new RideNotCompletedException("RIDE_NOT_COMPLETED");
        }

        Ride ride = rideService.getRide(rideId);
        ride.getDriver().setAvailable(true);
        ride.getRider().setActive(false);

        double amount = fareCalculator.calculateFare(distanceCalculator.calculateDistance(ride.getSource(), ride.getDestination()), ride.getTimeTaken());
        BillDto billDto = new BillDto();
        billDto.setAmount(amount);
        billDto.setDriverId(ride.getDriver().getDriverId());
        billDto.setRideId(ride.getRideId());

        System.out.println(billDto);
        return billDto;
    }
}
