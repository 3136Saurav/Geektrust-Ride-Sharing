package com.geektrust.ridesharing;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        // Redirect System.out to the outputStream
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        // Reset System.out to its original PrintStream
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Integration Test: SampleInput #1")
    public void sampleInput1Test() {
        String args = "sample_input/input1.txt";
        String expectedOutput = "DRIVERS_MATCHED D1 D3\n" +
                "RIDE_STARTED RIDE-001\n" +
                "RIDE_STOPPED RIDE-001\n" +
                "BILL RIDE-001 D3 186.72";

        Main.start(new String[]{args});

        Assertions.assertEquals(expectedOutput.replaceAll("\n", "").replaceAll("\r", ""), outputStream.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
    }


    @Test
    @DisplayName("Integration Test: SampleInput #2")
    public void sampleInput2Test() {
        String args = "sample_input/input2.txt";
        String expectedOutput = "DRIVERS_MATCHED D2 D3 D1\n" +
                "DRIVERS_MATCHED D1 D2 D3\n" +
                "RIDE_STARTED RIDE-101\n" +
                "RIDE_STARTED RIDE-102\n" +
                "RIDE_STOPPED RIDE-101\n" +
                "RIDE_STOPPED RIDE-102\n" +
                "BILL RIDE-101 D2 234.64\n" +
                "BILL RIDE-102 D1 258.00";

        Main.start(new String[]{args});

        Assertions.assertEquals(expectedOutput.replaceAll("\n", "").replaceAll("\r", ""), outputStream.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
    }

    @Test
    @DisplayName("Integration Test: SampleInput #3")
    public void sampleInput3Test() {
        String args = "sample_input/input3.txt";
        String expectedOutput = "NO_DRIVERS_AVAILABLE\n" +
                "DRIVERS_MATCHED D13 D4 D2\n" +
                "RIDE_STARTED RIDE-001\n" +
                "RIDE_STOPPED RIDE-001\n" +
                "BILL RIDE-001 D13 268.36";

        Main.start(new String[]{args});

        Assertions.assertEquals(expectedOutput.replaceAll("\n", "").replaceAll("\r", ""), outputStream.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
    }

    @Test
    @DisplayName("Integration Test: SampleInput #4")
    public void sampleInput4Test() {
        String args = "sample_input/input4.txt";
        String expectedOutput = "NO_DRIVERS_AVAILABLE\n" +
                "DRIVERS_MATCHED D1\n" +
                "DRIVERS_MATCHED D2 D1\n" +
                "DRIVERS_MATCHED D14 D15 D16 D1\n" +
                "DRIVERS_MATCHED D15 D2 D1\n" +
                "RIDE_STARTED RIDE-001\n" +
                "DRIVERS_MATCHED D14 D16 D17 D1\n" +
                "RIDE_STOPPED RIDE-001\n" +
                "BILL RIDE-001 D15 327.20\n" +
                "RIDE_STARTED RIDE-002\n" +
                "RIDE_STOPPED RIDE-002\n" +
                "INVALID_RIDE\n" +
                "BILL RIDE-002 D17 440.26\n" +
                "INVALID_RIDE\n" +
                "BILL RIDE-002 D17 440.26";

        Main.start(new String[]{args});

        Assertions.assertEquals(expectedOutput.replaceAll("\n", "").replaceAll("\r", ""), outputStream.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
    }

    @Test
    @DisplayName("Integration Test: SampleInput #5")
    public void sampleInput5Test() {
        String args = "sample_input/input5.txt";
        String expectedOutput = "DRIVERS_MATCHED D1\n" +
                "DRIVERS_MATCHED D3 D1 D2\n" +
                "RIDE_STARTED RIDE-001\n" +
                "DRIVERS_MATCHED D6 D7 D5 D3 D4\n" +
                "DRIVERS_MATCHED D5 D6 D7 D3\n" +
                "RIDE_STOPPED RIDE-001\n" +
                "RIDE_STARTED RIDE-002\n" +
                "RIDE_STARTED RIDE-003\n" +
                "BILL RIDE-001 D1 96.67\n" +
                "RIDE_STOPPED RIDE-002\n" +
                "RIDE_STOPPED RIDE-003\n" +
                "BILL RIDE-003 D6 62.40\n" +
                "BILL RIDE-002 D7 79.80";

        Main.start(new String[]{args});

        Assertions.assertEquals(expectedOutput.replaceAll("\n", "").replaceAll("\r", ""), outputStream.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
    }
    @Test
    @DisplayName("Integration Test: SampleInput #6")
    public void sampleInput6Test() {
        String args = "sample_input/input6.txt";
        String expectedOutput = "NO_DRIVERS_AVAILABLE\n" +
                "INVALID_RIDE\n" +
                "INVALID_RIDE\n" +
                "INVALID_RIDE";

        Main.start(new String[]{args});

        Assertions.assertEquals(expectedOutput.replaceAll("\n", "").replaceAll("\r", ""), outputStream.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
    }

}