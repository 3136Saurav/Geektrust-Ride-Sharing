package com.geektrust.ridesharing.util;

import com.geektrust.ridesharing.model.Location;

public class DistanceCalculator {

    public double calculateDistance(Location source, Location destination) {
        int x1 = source.getX();
        int y1 = source.getY();
        int x2 = destination.getX();
        int y2 = destination.getY();

        double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        return distance;
    }
}
