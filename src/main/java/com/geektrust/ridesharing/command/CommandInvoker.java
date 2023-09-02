package com.geektrust.ridesharing.command;

import com.geektrust.ridesharing.exceptions.InvalidCommandException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {
    private static final Map<String, Command> commandMap = new HashMap<>();

    public Command getCommand(String commandName) throws InvalidCommandException {
        Command command = commandMap.get(commandName);
        if (command == null) {
            throw new InvalidCommandException("INVALID_COMMAND_EXCEPTION");
        }
        return command;
    }

    public void executeCommand(String commandName, List<String> tokens) throws InvalidCommandException {
        Command command = getCommand(commandName);
        if (command == null) {
            throw new InvalidCommandException("INVALID_COMMAND_EXCEPTION");
        }
        command.execute(tokens);
    }


    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }
}