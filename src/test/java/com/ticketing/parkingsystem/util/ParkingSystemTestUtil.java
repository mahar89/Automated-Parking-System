package com.ticketing.parkingsystem.util;

import com.ticketing.parkingsystem.dto.ParkingStatusDto;
import com.ticketing.parkingsystem.entities.ParkedVehicle;
import com.ticketing.parkingsystem.entities.ParkingSpot;
import com.ticketing.parkingsystem.entities.ParkingTicket;
import com.ticketing.parkingsystem.entities.VehicleType;

import java.util.*;

public class ParkingSystemTestUtil {

  public static final String VEHICLE_REG_NUMBER = "KA-01-BB-0001";

  public static final String VEHICLE_COLOR = "white";

  public static final String NON_PARKED_VEHICLE_COLOR = "red";

  public static final int VALID_PARKING_SIZE = 6;

  public static final int INVALID_PARKING_SIZE = -1;

  public static final int PARKING_SIZE_MAX = Integer.MAX_VALUE;

  public static final int INVALID_PARKING_SPOT = Integer.MAX_VALUE;

  public static final int VALID_PARKING_SPOT = 1;

  public static final int NEAREST_PARKING_SPOT = 1;

  public static final int FAILED_PARKING_SPOT = -1;

  public static final List<String> VEHICLE_REG_NUMBERS = Arrays.asList("MH-01-KH-5141", "KA-01-HH-3141");

  public static final List<Integer> VEHICLE_PARKING_SPOT_NUMBERS = Arrays.asList(1, 2);

  public static List<ParkingSpot> createParkingSpotsStub(int parkingLotSize, boolean allOccupied) {
    List<ParkingSpot> parkingSpots = new ArrayList<>();
    ParkedVehicle parkedVehicle = allOccupied ? ParkedVehicle.builder().build() : null;
    int spotNumber = 1;
    while (parkingLotSize >= spotNumber) {
      parkingSpots.add(ParkingSpot.builder()
          .setSpotNumber(spotNumber)
          .setParkedVehicle(parkedVehicle)
          .build());
      spotNumber++;
    }
    return parkingSpots;
  }

  public static List<ParkingSpot> occupiedParkingSpotsStub() {
    List<ParkingSpot> parkingSpots = new ArrayList<>();
    for (ParkedVehicle parkedVehicle : getParkedVehiclesStub()) {
      parkingSpots.add(ParkingSpot.builder()
          .setSpotNumber(parkedVehicle.getParkingTicket().getParkingSpotNumber())
          .setParkedVehicle(parkedVehicle)
          .build());
    }

    return parkingSpots;
  }

  public static Optional<ParkingSpot> createParkingSpotStub(int spotNumber, boolean occupied) {

    ParkedVehicle parkedVehicle = occupied ? ParkedVehicle.builder().build() : null;

    return Optional.ofNullable(ParkingSpot.builder()
        .setSpotNumber(spotNumber)
        .setParkedVehicle(parkedVehicle)
        .build());
  }

  public static List<ParkingStatusDto> listOfParkingStatusDtoStub() {
    List<ParkingStatusDto> parkingStatusDtoList = new ArrayList<>();
    for (ParkedVehicle parkedVehicle : getParkedVehiclesStub()) {
      parkingStatusDtoList.add(ParkingStatusDto.builder()
          .setParkingSpotNumber(parkedVehicle.getParkingTicket().getParkingSpotNumber())
          .setVehicleColor(parkedVehicle.getColor())
          .setVehicleRegNumber(parkedVehicle.getRegNumber())
          .build());
    }

    return parkingStatusDtoList;
  }

  public static List<ParkedVehicle> getParkedVehiclesStub() {
    return Arrays.asList(
        ParkedVehicle.builder()
            .setColor(VEHICLE_COLOR)
            .setRegNumber(VEHICLE_REG_NUMBERS.get(0))
            .setType(VehicleType.CAR)
            .setParkingTicket(new ParkingTicket(VEHICLE_REG_NUMBERS.get(0), 1))
            .build(),
        ParkedVehicle.builder()
            .setColor(VEHICLE_COLOR)
            .setRegNumber(VEHICLE_REG_NUMBERS.get(1))
            .setType(VehicleType.CAR)
            .setParkingTicket(new ParkingTicket(VEHICLE_REG_NUMBERS.get(1), 2))
            .build());
  }
}
