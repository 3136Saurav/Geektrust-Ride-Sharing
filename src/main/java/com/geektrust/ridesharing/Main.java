package com.geektrust.ridesharing;

import com.geektrust.ridesharing.command.CommandInvoker;
import com.geektrust.ridesharing.config.AppConfig;
import com.geektrust.ridesharing.constants.CommonConstants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        start(args);
    }

    public static void start(String[] args) {
        AppConfig appConfig = new AppConfig();
        CommandInvoker commandInvoker = appConfig.getCommandRegistry();

        BufferedReader bufferedReader;
        String inputFileName = args[CommonConstants.ZERO];
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFileName));
            String line = bufferedReader.readLine();
            while (line != null) {
                List<String> tokens = Arrays.asList(line.split(" "));
                commandInvoker.executeCommand(tokens.get(CommonConstants.ZERO), tokens);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
