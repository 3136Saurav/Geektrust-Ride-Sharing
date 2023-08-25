package com.geektrust.ridesharing.manager;

import com.geektrust.ridesharing.pojo.Driver;

import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    // <driverId, Driver>
    private Map<String, Driver> driverMap = new HashMap<>();

    public void addDriver(Driver driver) {
        driverMap.put(driver.getDriverId(), driver);
    }

    public Map<String, Driver> getDriverMap() {
        return driverMap;
    }
}
