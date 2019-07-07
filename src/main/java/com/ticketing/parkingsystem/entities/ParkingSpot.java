package com.ticketing.parkingsystem.entities;

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

  public static ParkingSpot.ParkingSpotBuilder builder() {
    return new ParkingSpot.ParkingSpotBuilder();
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

  public static class ParkingSpotBuilder {
    private int spotNumber;
    private ParkedVehicle parkedVehicle;

    public ParkingSpot.ParkingSpotBuilder setSpotNumber(int spotNumber) {
      this.spotNumber = spotNumber;
      return this;
    }

    public ParkingSpot.ParkingSpotBuilder setParkedVehicle(ParkedVehicle parkedVehicle) {
      this.parkedVehicle = parkedVehicle;
      return this;
    }

    public ParkingSpot build() {
      return new ParkingSpot(spotNumber, parkedVehicle);
    }
  }
}
