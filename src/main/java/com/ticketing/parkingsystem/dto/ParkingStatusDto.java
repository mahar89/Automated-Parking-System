package com.ticketing.parkingsystem.dto;

import java.util.Objects;

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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ParkingStatusDto statusDto = (ParkingStatusDto) o;
    return Objects.equals(parkingSpotNumber, statusDto.parkingSpotNumber) &&
        Objects.equals(vehicleRegNumber, statusDto.vehicleRegNumber) &&
        Objects.equals(vehicleColor, statusDto.vehicleColor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parkingSpotNumber, vehicleRegNumber, vehicleColor);
  }

  @Override
  public String toString() {
    return "ParkingStatusDto{" +
        "parkingSpotNumber='" + parkingSpotNumber + '\'' +
        ", vehicleRegNumber='" + vehicleRegNumber + '\'' +
        ", vehicleColor='" + vehicleColor + '\'' +
        '}';
  }

  public static ParkingStatusDto.ParkingStatusDtoBuilder builder() {
    return new ParkingStatusDto.ParkingStatusDtoBuilder();
  }

  public static class ParkingStatusDtoBuilder {
    private int parkingSpotNumber;
    private String vehicleRegNumber;
    private String vehicleColor;

    public ParkingStatusDto.ParkingStatusDtoBuilder setParkingSpotNumber(int parkingSpotNumber) {
      this.parkingSpotNumber = parkingSpotNumber;
      return this;
    }

    public ParkingStatusDto.ParkingStatusDtoBuilder setVehicleRegNumber(String vehicleRegNumber) {
      this.vehicleRegNumber = vehicleRegNumber;
      return this;
    }

    public ParkingStatusDto.ParkingStatusDtoBuilder setVehicleColor(String vehicleColor) {
      this.vehicleColor = vehicleColor;
      return this;
    }

    public ParkingStatusDto build() {
      return new ParkingStatusDto(parkingSpotNumber, vehicleRegNumber, vehicleColor);
    }
  }
}
