package com.ticketing.parkingsystem.entities;

import java.util.Objects;

public final class ParkedVehicle {

  private final String regNumber;

  private final String color;

  private final VehicleType type;

  private final ParkingTicket parkingTicket;

  public ParkedVehicle(VehicleType type, String color, String regNumber, ParkingTicket parkingTicket) {
    this.color = color;
    this.type = type;
    this.regNumber = regNumber;
    this.parkingTicket = parkingTicket;
  }

  public String getRegNumber() {
    return regNumber;
  }

  public String getColor() {
    return color;
  }

  public ParkingTicket getParkingTicket() {
    return parkingTicket;
  }

  public static ParkedVehicle.ParkedVehicleBuilder builder() {
    return new ParkedVehicle.ParkedVehicleBuilder();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ParkedVehicle that = (ParkedVehicle) o;
    return Objects.equals(regNumber, that.regNumber) &&
        Objects.equals(color, that.color) &&
        type == that.type &&
        Objects.equals(parkingTicket, that.parkingTicket);
  }

  @Override
  public int hashCode() {
    return Objects.hash(regNumber, color, type, parkingTicket);
  }

  public static class ParkedVehicleBuilder {
    private VehicleType type;
    private String color;
    private String regNumber;
    private ParkingTicket parkingTicket;

    public ParkedVehicle.ParkedVehicleBuilder setType(VehicleType type) {
      this.type = type;
      return this;
    }

    public ParkedVehicle.ParkedVehicleBuilder setColor(String color) {
      this.color = color;
      return this;
    }

    public ParkedVehicle.ParkedVehicleBuilder setRegNumber(String regNumber) {
      this.regNumber = regNumber;
      return this;
    }

    public ParkedVehicle.ParkedVehicleBuilder setParkingTicket(ParkingTicket parkingTicket) {
      this.parkingTicket = parkingTicket;
      return this;
    }

    public ParkedVehicle build() {
      return new ParkedVehicle(type, color, regNumber, parkingTicket);
    }
  }
}
