package com.geektrust.ridesharing.manager;

import com.geektrust.ridesharing.pojo.Bill;
import com.geektrust.ridesharing.pojo.Ride;
import com.geektrust.ridesharing.util.DistanceCalculator;
import com.geektrust.ridesharing.util.FareCalculator;

public class BillManager {
    FareCalculator fareCalculator;
    DistanceCalculator distanceCalculator;

    public BillManager(FareCalculator fareCalculator, DistanceCalculator distanceCalculator) {
        this.fareCalculator = fareCalculator;
        this.distanceCalculator = distanceCalculator;
    }

    public Bill createBill(Ride ride) {
        Bill bill = new Bill();
        double distanceTravelled = distanceCalculator.calculateDistance(ride.getSource(), ride.getDestination());
        bill.setDistanceTravelled(distanceTravelled);
        bill.setFare(fareCalculator.calculateFare(distanceTravelled, ride.getTimeTaken()));
        bill.setRideId(ride.getRideId());
        bill.setDriverId(ride.getDriverId());
        return bill;
    }
}
