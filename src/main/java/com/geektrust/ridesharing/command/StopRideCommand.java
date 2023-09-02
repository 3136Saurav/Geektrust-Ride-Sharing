package com.geektrust.ridesharing.command;

import com.geektrust.ridesharing.constants.CommonConstants;
import com.geektrust.ridesharing.exceptions.InvalidRideException;
import com.geektrust.ridesharing.exceptions.RideNotCompletedException;
import com.geektrust.ridesharing.services.RideService;

import java.util.List;

public class StopRideCommand implements Command {
    private RideService rideService;

    public StopRideCommand(RideService rideService) {
        this.rideService = rideService;
    }

    @Override
    public void execute(List<String> tokens) {
        String rideId = tokens.get(CommonConstants.ONE);
        int destinationX = Integer.parseInt(tokens.get(CommonConstants.TWO));
        int destinationY = Integer.parseInt(tokens.get(CommonConstants.THREE));
        int timeTaken = Integer.parseInt(tokens.get(CommonConstants.FOUR));

        try {
            rideService.stopRide(rideId, destinationX, destinationY, timeTaken);
        } catch (InvalidRideException e) {
            System.out.println(e.getMessage());
        } catch (RideNotCompletedException e) {
            System.out.println(e.getMessage());
        }
    }
}
