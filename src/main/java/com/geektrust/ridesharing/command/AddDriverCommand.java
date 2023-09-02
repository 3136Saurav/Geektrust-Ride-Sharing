package com.geektrust.ridesharing.command;

import com.geektrust.ridesharing.constants.CommonConstants;
import com.geektrust.ridesharing.model.Driver;
import com.geektrust.ridesharing.model.Location;
import com.geektrust.ridesharing.services.DriverService;

import java.util.List;

public class AddDriverCommand implements Command{
    private final DriverService driverService;

    public AddDriverCommand(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public void execute(List<String> tokens) {
        String driverId =  tokens.get(CommonConstants.ONE);
        int driverXCoordinate = Integer.parseInt(tokens.get(CommonConstants.TWO));
        int driverYCoordinate = Integer.parseInt(tokens.get(CommonConstants.THREE));

        Driver driver = new Driver();
        driver.setDriverId(driverId);
        driver.setLocation(new Location(driverXCoordinate, driverYCoordinate));
        driverService.addDriver(driver);
    }
}
