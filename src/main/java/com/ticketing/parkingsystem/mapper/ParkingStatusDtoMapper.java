package com.ticketing.parkingsystem.mapper;

import com.ticketing.parkingsystem.dto.ParkingStatusDto;
import com.ticketing.parkingsystem.entities.ParkingSpot;

public class ParkingStatusDtoMapper {

  public ParkingStatusDto parkingSpotToParkingStatusDto(ParkingSpot parkingSpot) {

    return ParkingStatusDto.builder()
        .setParkingSpotNumber(parkingSpot.getSpotNumber())
        .setVehicleRegNumber(parkingSpot.getParkedVehicle().getRegNumber())
        .setVehicleColor(parkingSpot.getParkedVehicle().getColor())
        .build();
  }
}
