package com.geektrust.ridesharing.command;


import com.geektrust.ridesharing.constants.CommonConstants;
import com.geektrust.ridesharing.exceptions.InvalidRideException;
import com.geektrust.ridesharing.services.RideService;

import java.util.List;

public class MatchCommand implements Command {
    private final RideService rideService;

    public MatchCommand(RideService rideService) {
        this.rideService = rideService;
    }

    @Override
    public void execute(List<String> tokens){
        String riderId = tokens.get(CommonConstants.ONE);
        try {
            rideService.matchRider(riderId);
        } catch (InvalidRideException e) {
            System.out.println(e.getMessage());
        }
    }
}
