package com.ticketing.parkingsystem.entities;

import com.ticketing.parkingsystem.entities.builder.ParkingSpotBuilder;

import java.util.Objects;

public final class ParkingSpot {

  private final int spotNumber;
  private final ParkedVehicle parkedVehicle;

  public ParkingSpot(int spotNumber, ParkedVehicle parkedVehicle) {
    this.spotNumber = spotNumber;
    this.parkedVehicle = parkedVehicle;
  }

  public int getSpotNumber() {
    return spotNumber;
  }

  public ParkedVehicle getParkedVehicle() {
    return parkedVehicle;
  }

  public boolean isFree() {
    return null == parkedVehicle;
  }

  public static ParkingSpotBuilder builder() {
    return new ParkingSpotBuilder();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ParkingSpot that = (ParkingSpot) o;
    return spotNumber == that.spotNumber;
  }

  @Override
  public int hashCode() {
    return Objects.hash(spotNumber);
  }
}
