package com.ticketing.parkingsystem.dao;

import com.ticketing.parkingsystem.dao.custom.ParkedVehicleDao;
import com.ticketing.parkingsystem.dao.custom.ParkingSpotDao;
import com.ticketing.parkingsystem.entities.ParkedVehicle;
import com.ticketing.parkingsystem.entities.ParkingSpot;

public class ParkingSystemDaoFactory {

  public static Dao<ParkingSpot> getParkingSpotDao(String type) {

    switch (type) {
      case "local":
      default:
        return new ParkingSpotDao();
    }
  }

  public static Dao<ParkedVehicle> getParkedVehicleDao(String type) {

    switch (type) {
      case "local":
      default:
        return new ParkedVehicleDao();
    }
  }

}
