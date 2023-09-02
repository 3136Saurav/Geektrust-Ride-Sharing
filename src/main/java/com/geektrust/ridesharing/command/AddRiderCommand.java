package com.geektrust.ridesharing.command;

import com.geektrust.ridesharing.constants.CommonConstants;
import com.geektrust.ridesharing.model.Location;
import com.geektrust.ridesharing.model.Rider;
import com.geektrust.ridesharing.services.RiderService;

import java.util.List;

public class AddRiderCommand implements Command {

    private final RiderService riderService;

    public AddRiderCommand(RiderService riderService) {
        this.riderService = riderService;
    }

    @Override
    public void execute(List<String> tokens) {
        String riderId =  tokens.get(CommonConstants.ONE);
        int riderXCoordinate = Integer.parseInt(tokens.get(CommonConstants.TWO));
        int riderYCoordinate = Integer.parseInt(tokens.get(CommonConstants.THREE));

        Rider rider = new Rider();
        rider.setRiderId(riderId);
        rider.setLocation(new Location(riderXCoordinate, riderYCoordinate));
        riderService.addRider(rider);
    }
}
