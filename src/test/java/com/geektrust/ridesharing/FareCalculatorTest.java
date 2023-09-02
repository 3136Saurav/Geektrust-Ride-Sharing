package com.geektrust.ridesharing;

import com.geektrust.ridesharing.util.FareCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FareCalculatorTest {
    FareCalculator fareCalculator = new FareCalculator();

    @Test
    public void fareCalculatorTest() {
        double fare = fareCalculator.calculateFare(6.403, 32);
        Assertions.assertEquals(186.72, fare, 0.05);
    }
}
