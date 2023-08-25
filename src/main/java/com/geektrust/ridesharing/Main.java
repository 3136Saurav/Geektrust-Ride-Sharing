package com.geektrust.ridesharing;

import com.geektrust.ridesharing.manager.*;
import com.geektrust.ridesharing.pojo.Bill;
import com.geektrust.ridesharing.pojo.Driver;
import com.geektrust.ridesharing.pojo.Location;
import com.geektrust.ridesharing.pojo.Rider;
import com.geektrust.ridesharing.util.DistanceCalculator;
import com.geektrust.ridesharing.util.FareCalculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;

public class Main {
    static DistanceCalculator distanceCalculator = new DistanceCalculator();
    static FareCalculator fareCalculator = new FareCalculator();
    static DriverManager driverManager = new DriverManager();
    static RiderManager riderManager = new RiderManager();
    static BillManager billManager = new BillManager(fareCalculator, distanceCalculator);
    static RideManager rideManager = new RideManager(driverManager, riderManager, billManager, distanceCalculator);
    static DecimalFormat df = new DecimalFormat("#.00");

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("sample_input/input2.txt");
        parseInputFromFile(path);
    }

    public static List<String> parseInputFromFile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String[] input = line.split(" ");
            String command = input[0];
            if ("ADD_DRIVER".equalsIgnoreCase(command)) {
                String driverId = input[1];
                int driverXCoordinate = Integer.parseInt(input[2]);
                int driverYCoordinate = Integer.parseInt(input[3]);
                Driver driver = new Driver(driverId, new Location(driverXCoordinate, driverYCoordinate));
                driverManager.addDriver(driver);
            } else if ("ADD_RIDER".equalsIgnoreCase(command)) {
                String riderId = input[1];
                int riderXCoordinate = Integer.parseInt(input[2]);
                int riderYCoordinate = Integer.parseInt(input[3]);
                Rider rider = new Rider(riderId, new Location(riderXCoordinate, riderYCoordinate));
                riderManager.addRider(rider);
            } else if ("MATCH".equalsIgnoreCase(command)) {
                String riderId = input[1];
                List<Driver> driverList = rideManager.matchRide(riderId);
                if (driverList.isEmpty()) {
                    System.out.println("NO_DRIVERS_AVAILABLE");
                } else {
                    System.out.print("DRIVERS_MATCHED ");
                    for (Driver driver : driverList) {
                        System.out.print(driver.getDriverId() + " ");
                    }
                    System.out.println();
                    rideManager.getRiderToDriversMap().put(riderId, driverList);
                }
            } else if ("START_RIDE".equalsIgnoreCase(command)) {
                String rideId = input[1];
                int driverIndex = Integer.parseInt(input[2]) - 1;
                String riderId = input[3];

                List<Driver> driverList = rideManager.getRiderToDriversMap().get(riderId);
                if (driverIndex < 0 || driverIndex >= driverList.size()) {
                    System.out.println("INVALID_RIDE");
                } else {
                    System.out.println("RIDE_STARTED " + rideId);
                    rideManager.startRide(rideId, riderId, driverList.get(1).getDriverId());
                }
            } else if ("STOP_RIDE".equalsIgnoreCase(command)) {
                String rideId = input[1];
                int destinationX = Integer.parseInt(input[2]);
                int destinationY = Integer.parseInt(input[3]);
                int timeTaken = Integer.parseInt(input[4]);
                rideManager.stopRide(rideId, new Location(destinationX, destinationY), timeTaken);
                System.out.println("RIDE_STOPPED " + rideId);
            } else if ("BILL".equalsIgnoreCase(command)) {
                String rideId = input[1];
                Bill bill = rideManager.getBill(rideId);
                System.out.println("BILL " + rideId + " " + bill.getDriverId() + " " + df.format(bill.getFare()));
            }

        }

        return lines;
    }
}
