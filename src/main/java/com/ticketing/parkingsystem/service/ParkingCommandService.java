package com.ticketing.parkingsystem.service;

import com.ticketing.parkingsystem.config.ParkingSystemConfig;
import com.ticketing.parkingsystem.dao.Dao;
import com.ticketing.parkingsystem.entities.ParkedVehicle;
import com.ticketing.parkingsystem.entities.ParkingSpot;
import com.ticketing.parkingsystem.entities.ParkingTicket;
import com.ticketing.parkingsystem.entities.VehicleType;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ParkingCommandService {

  private final Dao<ParkingSpot> parkingSpotDao;

  private final Dao<ParkedVehicle> parkedVehicleDao;

  public ParkingCommandService(Dao<ParkingSpot> parkingSpotDao,
                               Dao<ParkedVehicle> parkedVehicleDao) {
    this.parkingSpotDao = parkingSpotDao;
    this.parkedVehicleDao = parkedVehicleDao;
  }

  public int parkVehicle(String regNumber, String color) {
    int availableSpotNumber = -1;

    if (null == regNumber || null == color) {
      return availableSpotNumber;
    }

    List<ParkingSpot> parkingSpots = parkingSpotDao.getAll();

    List<ParkingSpot> availableParkingSpots = parkingSpots.stream()
        .filter(ParkingSpot::isFree)
        .collect(Collectors.toList());

    if (!availableParkingSpots.isEmpty()) {

      availableParkingSpots.sort(Comparator.comparing(ParkingSpot::getSpotNumber));
      ParkingSpot availableParkingSpot = availableParkingSpots.remove(0);
      availableSpotNumber = availableParkingSpot.getSpotNumber();

      ParkingTicket parkingTicket = new ParkingTicket(regNumber, availableSpotNumber);
      ParkedVehicle parkedVehicle = ParkedVehicle.builder()
          .setRegNumber(regNumber)
          .setColor(color)
          .setParkingTicket(parkingTicket)
          .setType(VehicleType.CAR)
          .build();
      ParkingSpot parkingSpot = ParkingSpot.builder()
          .setSpotNumber(availableSpotNumber)
          .setParkedVehicle(parkedVehicle)
          .build();

      parkingSpotDao.upsert(parkingSpot);
      parkedVehicleDao.upsert(parkedVehicle);
    }

    return availableSpotNumber;
  }

  public boolean createParkingLot(int size) {
    boolean created = false;
    if (parkingSpotDao.getAll().isEmpty() &&
        size >=1 && size <= ParkingSystemConfig.getMaxAllowedParkingLotSize() ) {
      created = true;
      int spotNumber = 1;
      while (size >= spotNumber) {
        parkingSpotDao.upsert(ParkingSpot.builder()
            .setSpotNumber(spotNumber)
            .build());
        spotNumber++;
      }
    }

    return created;
  }

  public boolean leaveParkingSpot(int spotNumber) {
    boolean successfullyLeft;
    Optional<ParkingSpot> parkingSpotOptional = parkingSpotDao.getById(String.valueOf(spotNumber));

    if (successfullyLeft = parkingSpotOptional.isPresent() && !parkingSpotOptional.get().isFree()) {
      ParkingSpot bookedParkingSpot = parkingSpotOptional.get();
      ParkingSpot freeParkingSpot = ParkingSpot.builder()
          .setSpotNumber(spotNumber)
          .build();

      parkedVehicleDao.delete(bookedParkingSpot.getParkedVehicle());
      parkingSpotDao.upsert(freeParkingSpot);
    }

    return successfullyLeft;
  }
}
