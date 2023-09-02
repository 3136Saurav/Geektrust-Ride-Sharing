package com.geektrust.ridesharing.config;

import com.geektrust.ridesharing.app.RideShareApp;
import com.geektrust.ridesharing.command.*;
import com.geektrust.ridesharing.services.BillService;
import com.geektrust.ridesharing.services.DriverService;
import com.geektrust.ridesharing.services.RideService;
import com.geektrust.ridesharing.services.RiderService;
import com.geektrust.ridesharing.util.DistanceCalculator;
import com.geektrust.ridesharing.util.FareCalculator;

public class AppConfig {

    private final RideShareApp rideShareApp = new RideShareApp();
    private final DistanceCalculator distanceCalculator = new DistanceCalculator();
    private final FareCalculator fareCalculator = new FareCalculator();
    private final DriverService driverService = new DriverService(rideShareApp);
    private final RiderService riderService = new RiderService(rideShareApp);
    private final RideService rideService = new RideService(rideShareApp, driverService, distanceCalculator, riderService);
    private final BillService billService = new BillService(rideService, fareCalculator, distanceCalculator);
    private Command addDriverCommand = new AddDriverCommand(driverService);
    private Command addRiderCommand = new AddRiderCommand(riderService);
    private Command billCommand = new BillCommand(billService);
    private Command matchCommand = new MatchCommand(rideService);
    private Command startRideCommand = new StartRideCommand(rideService);
    private Command stopRideCommand = new StopRideCommand(rideService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandRegistry() {
        commandInvoker.register("ADD_DRIVER", addDriverCommand);
        commandInvoker.register("ADD_RIDER", addRiderCommand);
        commandInvoker.register("MATCH", matchCommand);
        commandInvoker.register("START_RIDE", startRideCommand);
        commandInvoker.register("STOP_RIDE", stopRideCommand);
        commandInvoker.register("BILL", billCommand);

        return commandInvoker;
    }

}
