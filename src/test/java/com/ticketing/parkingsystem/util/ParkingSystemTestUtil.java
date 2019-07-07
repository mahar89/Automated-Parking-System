package com.ticketing.parkingsystem.util;

import com.ticketing.parkingsystem.entities.ParkedVehicle;
import com.ticketing.parkingsystem.entities.ParkingSpot;

import java.util.*;

public class ParkingSystemTestUtil {

  public static final String VEHICLE_REG_NUMBER = "KA-01-BB-0001";
  public static final String VEHICLE_COLOR = "white";

  public static final int VALID_PARKING_SIZE = 6;

  public static final int INVALID_PARKING_SIZE = -1;

  public static final int PARKING_SIZE_MAX = Integer.MAX_VALUE;

  public static final int INVALID_PARKING_SPOT = Integer.MAX_VALUE;

  public static final int VALID_PARKING_SPOT = 1;

  public static final int NEAREST_PARKING_SPOT = 1;

  public static final int FAILED_PARKING_SPOT = -1;

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

  public static Optional<ParkingSpot> createParkingSpotStub(int spotNumber, boolean occupied) {

    ParkedVehicle parkedVehicle = occupied ? ParkedVehicle.builder().build() : null;

    return Optional.ofNullable(ParkingSpot.builder()
        .setSpotNumber(spotNumber)
        .setParkedVehicle(parkedVehicle)
        .build());
  }
}
