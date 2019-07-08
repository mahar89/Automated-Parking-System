package com.ticketing.parkingsystem.controller;

import com.ticketing.parkingsystem.dto.ParkingStatusDto;
import com.ticketing.parkingsystem.service.ParkingQueryService;

import java.util.Arrays;
import java.util.List;

public class ParkingQueryController {

  private ParkingQueryService parkingQueryService;

  public ParkingQueryController(ParkingQueryService parkingQueryService) {
    this.parkingQueryService = parkingQueryService;
  }

  public void status(List<String> args) {
    List<ParkingStatusDto> parkingStatusDtoList = parkingQueryService.showParkingStatus();

    System.out.printf("%-10.10s  %-19.20s %-10.10s%n", "Slot No.", "Registration No", "Colour");

    for (ParkingStatusDto statusDto : parkingStatusDtoList) {
      System.out.printf("%-10.10s  %-19.20s %-10.10s%n", statusDto.getParkingSpotNumber(), statusDto.getVehicleRegNumber(), statusDto.getVehicleColor());

    }
  }

  public void getParkingSpotForRegNumber(List<String> args) {
    int parkingSpotNumber = parkingQueryService.getParkingSpotNumberForVehicleWithRegNumber(args.get(0));
    String msg = -1 == parkingSpotNumber ? "Not found" : String.valueOf(parkingSpotNumber);

    System.out.println(msg);
  }

  public void getParkingSpotsWithColor(List<String> args) {
    List<Integer> parkingSpotNumbers = parkingQueryService.getParkingSpotNumbersOfVehiclesWithColor(args.get(0));

    String msg = !parkingSpotNumbers.isEmpty() ?
        Arrays.toString(parkingSpotNumbers.toArray()).replace("[", "").replace("]", "") : "Not found";

    System.out.println(msg);
  }

  public void getRegNumbersWithColor(List<String> args) {
    List<String> regNumbers = parkingQueryService.getVehicleRegNumbersOfVehiclesWithColor(args.get(0));

    String msg = !regNumbers.isEmpty() ?
        Arrays.toString(regNumbers.toArray()).replace("[", "").replace("]", "") : "Not found";

    System.out.println(msg);
  }

}
