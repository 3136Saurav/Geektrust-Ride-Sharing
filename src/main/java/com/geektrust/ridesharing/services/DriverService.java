package com.geektrust.ridesharing.services;

import com.geektrust.ridesharing.model.Driver;
import com.geektrust.ridesharing.app.RideShareApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DriverService {
    RideShareApp rideShareApp;

    public DriverService(RideShareApp rideShareApp) {
        this.rideShareApp = rideShareApp;
    }

    public void addDriver(Driver driver) {
        rideShareApp.addDriver(driver);
    }

    public List<Driver> getAllAvailableDrivers() {
        List<Driver> driverList = new ArrayList<>();
        Map<String, Driver> driverMap = rideShareApp.getDriverMap();
        for (Map.Entry<String, Driver> entry : driverMap.entrySet()) {
            Driver driver = entry.getValue();
            if (driver.isAvailable()) {
                driverList.add(driver);
            }
        }
        return driverList;
    }
}
