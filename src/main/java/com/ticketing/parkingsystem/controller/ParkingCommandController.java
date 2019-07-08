package com.ticketing.parkingsystem.controller;

import com.ticketing.parkingsystem.service.ParkingCommandService;

import java.util.List;

public class ParkingCommandController {

  private ParkingCommandService parkingCommandService;

  public ParkingCommandController(ParkingCommandService parkingCommandService) {
    this.parkingCommandService = parkingCommandService;
  }

  public void park(List<String> args) {
    int parkingSpotNumber = parkingCommandService.parkVehicle(args.get(0), args.get(1));

    String msg = -1 == parkingSpotNumber ? "Sorry, parking lot is full" :
        String.format("Allocated slot number: %s", parkingSpotNumber);

    System.out.println(msg);
  }

  public void createParkingLot(List<String> args) {
    boolean isCreated = parkingCommandService.createParkingLot(Integer.parseInt(args.get(0)));

    String msg = isCreated ? String.format("Created a parking lot with %s slots", args.get(0)) : "Invalid Operation...";

    System.out.println(msg);
  }

  public void leave(List<String> args) {
    boolean isFree = parkingCommandService.leaveParkingSpot(Integer.parseInt(args.get(0)));

    String msg = isFree ? String.format("Slot number %s is free", args.get(0)) : "Not found";

    System.out.println(msg);
  }
}
