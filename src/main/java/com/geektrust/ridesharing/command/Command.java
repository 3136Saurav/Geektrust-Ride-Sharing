package com.geektrust.ridesharing.command;

import java.util.List;

public interface Command {
    public void execute(List<String> tokens);
}
