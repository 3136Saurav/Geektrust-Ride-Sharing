package com.geektrust.ridesharing.command;

import com.geektrust.ridesharing.constants.CommonConstants;
import com.geektrust.ridesharing.exceptions.InvalidRideException;
import com.geektrust.ridesharing.services.RideService;

import java.util.List;

public class StartRideCommand implements Command {
    RideService rideService;

    public StartRideCommand(RideService rideService) {
        this.rideService = rideService;
    }

    @Override
    public void execute(List<String> tokens) {
        String rideId = tokens.get(CommonConstants.ONE);
        int driverNumber = Integer.parseInt(tokens.get(CommonConstants.TWO));
        String riderId = tokens.get(CommonConstants.THREE);

        try {
            rideService.startRide(rideId, driverNumber, riderId);
        } catch(InvalidRideException e) {
            System.out.println(e.getMessage());
        }
    }
}