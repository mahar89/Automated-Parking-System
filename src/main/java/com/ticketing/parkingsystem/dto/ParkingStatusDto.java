package com.ticketing.parkingsystem.dto;

public final class ParkingStatusDto {

  private final String parkingSpotNumber;

  private final String vehicleRegNumber;

  private final String vehicleColor;

  public ParkingStatusDto(int parkingSpotNumber, String vehicleRegNumber, String vehicleColor) {
    this.parkingSpotNumber = String.valueOf(parkingSpotNumber);
    this.vehicleRegNumber = vehicleRegNumber;
    this.vehicleColor = vehicleColor;
  }

  public String getParkingSpotNumber() {
    return parkingSpotNumber;
  }

  public String getVehicleRegNumber() {
    return vehicleRegNumber;
  }

  public String getVehicleColor() {
    return vehicleColor;
  }

  @Override
  public String toString() {
    return "ParkingStatusDto{" +
        "parkingSpotNumber='" + parkingSpotNumber + '\'' +
        ", vehicleRegNumber='" + vehicleRegNumber + '\'' +
        ", vehicleColor='" + vehicleColor + '\'' +
        '}';
  }
}
