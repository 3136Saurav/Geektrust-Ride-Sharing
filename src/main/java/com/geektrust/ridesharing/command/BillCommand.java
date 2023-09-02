package com.geektrust.ridesharing.command;

import com.geektrust.ridesharing.constants.CommonConstants;
import com.geektrust.ridesharing.exceptions.InvalidRideException;
import com.geektrust.ridesharing.exceptions.RideNotCompletedException;
import com.geektrust.ridesharing.services.BillService;

import java.util.List;

public class BillCommand implements Command {
    private BillService billService;

    public BillCommand(BillService billService) {
        this.billService = billService;
    }

    @Override
    public void execute(List<String> tokens) {
        String rideId = tokens.get(CommonConstants.ONE);
        try {
            billService.generateBill(rideId);
        } catch (InvalidRideException e) {
            System.out.println(e.getMessage());
        } catch (RideNotCompletedException e) {
            System.out.println(e.getMessage());
        }

    }
}