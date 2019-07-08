package com.ticketing.parkingsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParkingSystemApp {

  public static void main(String[] args) throws IOException {

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    File file = new File(classloader.getResource("file_inputs.txt").getFile());
    executeCommands(file);
  }

  private static void executeCommands(File file) throws IOException {

    if (file == null) return;

    try (FileReader reader = new FileReader(file);
         BufferedReader br = new BufferedReader(reader)) {
      AutomatedParkingSimulator automatedParkingSimulator = AutomatedParkingSimulator.getInstance();
      String line;
      while ((line = br.readLine()) != null) {
        String[] args = line.split(" ");
        List<String> remArgs = new ArrayList<>();
        for (int i = 1; i < args.length; i++ ) {
          remArgs.add(args[i]);
        }

        try {
          automatedParkingSimulator.getMethodForCommand(args[0]).accept(remArgs);
        } catch (Exception e) {
          System.out.println("Invalid Operation...");
        }
      }
    }
  }
}



