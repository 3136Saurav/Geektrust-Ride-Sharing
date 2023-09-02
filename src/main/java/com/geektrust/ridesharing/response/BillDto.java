package com.geektrust.ridesharing.response;

import java.text.DecimalFormat;

public class BillDto {
    private String rideId;
    private String driverId;
    private double amount;
    private static DecimalFormat df = new DecimalFormat("#.00");

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BILL " + rideId + " " + driverId + " " + df.format(amount);
    }
}
