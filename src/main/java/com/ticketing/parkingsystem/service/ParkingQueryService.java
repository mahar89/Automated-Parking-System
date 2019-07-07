package com.ticketing.parkingsystem.service;

import com.ticketing.parkingsystem.dto.ParkingStatusDto;

import java.util.Collections;
import java.util.List;

public class ParkingQueryService {

  public List<ParkingStatusDto> showParkingStatus() {
    return Collections.emptyList();
  }

  public List<String> getVehicleRegNumbersOfVehiclesWithColor(String vehicleColor) {
    return Collections.emptyList();
  }

  public List<Integer> getParkingSpotNumbersOfVehiclesWithColor(String vehicleColor) {
    return Collections.emptyList();
  }

  public int getParkingSpotNumberForVehicleWithRegNumber(String vehicleRegNumber) {
    return -1;
  }
}
