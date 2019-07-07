package com.ticketing.parkingsystem.service;

import com.ticketing.parkingsystem.config.ParkingSystemConfig;
import com.ticketing.parkingsystem.dao.Dao;
import com.ticketing.parkingsystem.entities.ParkingSpot;

public class ParkingCommandService {

  private final Dao<ParkingSpot> parkingSpotDao;

  public ParkingCommandService(Dao<ParkingSpot> parkingSpotDao) {
    this.parkingSpotDao = parkingSpotDao;
  }

  public int parkVehicle(String regNumber, String color) {
    int availableSpotNumber = -1;
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
    return false;
  }
}
