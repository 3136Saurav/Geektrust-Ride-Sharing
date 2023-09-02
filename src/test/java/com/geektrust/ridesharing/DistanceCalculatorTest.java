package com.geektrust.ridesharing;

import com.geektrust.ridesharing.model.Location;
import com.geektrust.ridesharing.util.DistanceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DistanceCalculatorTest {
    DistanceCalculator distanceCalculator = new DistanceCalculator();

    @Test
    @DisplayName("Test: Distance Calculator")
    public void distanceCalculatorTest() {
        Location source = new Location(0, 0);
        Location destination = new Location(3, 4);

        double distance = distanceCalculator.calculateDistance(source, destination);
        Assertions.assertEquals(5.00, distance, 0.05);
    }
}
