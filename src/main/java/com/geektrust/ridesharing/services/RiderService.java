package com.geektrust.ridesharing.services;

import com.geektrust.ridesharing.app.RideShareApp;
import com.geektrust.ridesharing.model.Rider;

public class RiderService {
    RideShareApp rideShareApp;

    public RiderService(RideShareApp rideShareApp) {
        this.rideShareApp = rideShareApp;
    }

    public void addRider(Rider rider) {
        rideShareApp.addRider(rider);
    }


    public boolean isRiderActive(String riderId) {
        Rider rider = rideShareApp.getRiderMap().get(riderId);
        if (rider == null) return false;
        return rider.isActive();
    }

    public boolean isRiderExists(String riderId) {
        return rideShareApp.getRiderMap().containsKey(riderId);
    }

    public Rider getRider(String riderId) {
        return rideShareApp.getRiderMap().get(riderId);
    }
}
