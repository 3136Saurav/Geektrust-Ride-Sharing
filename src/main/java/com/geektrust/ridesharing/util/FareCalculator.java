package com.geektrust.ridesharing.util;


import com.geektrust.ridesharing.constants.CommonConstants;

public class FareCalculator {
    public double calculateFare(double distanceTravelled, long timeTaken) {
        double totalFare = (CommonConstants.BASE_FARE
                            + (distanceTravelled * CommonConstants.FARE_PER_KM)
                            + (CommonConstants.FARE_PER_MINUTE * timeTaken)
                            ) * (1.20);
        return totalFare;
    }
}
