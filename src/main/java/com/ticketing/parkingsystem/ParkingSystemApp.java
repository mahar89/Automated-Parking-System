package com.ticketing.parkingsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ParkingSystemApp {

  public static void main(String[] args) {

    try {
      if (args.length != 0) {
        File file = new File(args[0]);
        executeCommands(file);
      } else {
        Scanner input = new Scanner(System.in);
        executeCommands(input);
      }
    } catch (Exception e) {
      System.out.println("Invalid operation...");
    }

  }

  private static void executeCommands(Scanner input) {
    AutomatedParkingSimulator automatedParkingSimulator = AutomatedParkingSimulator.getInstance();
    System.out
        .println("Type 'help' to get the list of commands.");
    System.out
        .println("Type 'stop' to stop the application.");
    System.out
        .println("Start typing.... \n");

    do {
      String next = input.nextLine();
      String[] inputArgs = next.split(" ");

      String command = inputArgs[0];

      if (command.trim().equalsIgnoreCase("help")) {
        System.out
            .println("Type 'park' followed by Vehicle Registration Number and Colour.");
        System.out
            .println("Type 'leave' followed by parking spot number");
        System.out
            .println("Type 'status' to get parking status.");
        System.out
            .println("Type 'registration_numbers_for_cars_with_colour' followed by color.");
        System.out
            .println("Type 'slot_numbers_for_cars_with_colour' followed by color.");
        System.out
            .println("Type 'slot_number_for_registration_number' followed by color.");

      } else if (command.trim().equalsIgnoreCase("stop")) {
        break;
      } else {
        List<String> remInputArgs = Arrays.asList(inputArgs).subList(1, inputArgs.length);
        automatedParkingSimulator.getMethodForCommand(inputArgs[0]).accept(remInputArgs);
      }
    } while (true);
  }

  private static void executeCommands(File file) throws IOException {

    if (file == null) {
      return;
    }

    AutomatedParkingSimulator automatedParkingSimulator = AutomatedParkingSimulator.getInstance();

    try (FileReader reader = new FileReader(file);
         BufferedReader br = new BufferedReader(reader)) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] args = line.split(" ");
        List<String> remArgs = Arrays.asList(args).subList(1, args.length);
        automatedParkingSimulator.getMethodForCommand(args[0]).accept(remArgs);
      }
    }
  }
}



