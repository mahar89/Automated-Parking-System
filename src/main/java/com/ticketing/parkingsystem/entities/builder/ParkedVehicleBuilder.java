package com.ticketing.parkingsystem.entities.builder;

import com.ticketing.parkingsystem.entities.ParkedVehicle;
import com.ticketing.parkingsystem.entities.ParkingTicket;
import com.ticketing.parkingsystem.entities.VehicleType;

public class ParkedVehicleBuilder {
  private VehicleType type;
  private String color;
  private String regNumber;
  private ParkingTicket parkingTicket;

  public ParkedVehicleBuilder setType(VehicleType type) {
    this.type = type;
    return this;
  }

  public ParkedVehicleBuilder setColor(String color) {
    this.color = color;
    return this;
  }

  public ParkedVehicleBuilder setRegNumber(String regNumber) {
    this.regNumber = regNumber;
    return this;
  }

  public ParkedVehicleBuilder setParkingTicket(ParkingTicket parkingTicket) {
    this.parkingTicket = parkingTicket;
    return this;
  }

  public ParkedVehicle build() {
    return new ParkedVehicle(type, color, regNumber, parkingTicket);
  }
}