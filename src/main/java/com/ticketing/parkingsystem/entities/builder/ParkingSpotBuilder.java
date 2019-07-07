package com.ticketing.parkingsystem.entities.builder;

import com.ticketing.parkingsystem.entities.ParkedVehicle;
import com.ticketing.parkingsystem.entities.ParkingSpot;

public class ParkingSpotBuilder {
  private int spotNumber;
  private ParkedVehicle parkedVehicle;

  public ParkingSpotBuilder setSpotNumber(int spotNumber) {
    this.spotNumber = spotNumber;
    return this;
  }

  public ParkingSpotBuilder setParkedVehicle(ParkedVehicle parkedVehicle) {
    this.parkedVehicle = parkedVehicle;
    return this;
  }

  public ParkingSpot build() {
    return new ParkingSpot(spotNumber, parkedVehicle);
  }
}