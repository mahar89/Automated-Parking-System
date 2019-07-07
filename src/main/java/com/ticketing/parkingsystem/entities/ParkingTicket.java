package com.ticketing.parkingsystem.entities;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class ParkingTicket {

  private final String ticketId;

  private final String vehicleRegNumber;

  private final int parkingSpotNumber;

  private final Instant inTime;

  public ParkingTicket(String vehicleRegNumber, int parkingSpotNumber) {
    this.ticketId = UUID.randomUUID().toString();
    this.vehicleRegNumber = vehicleRegNumber;
    this.parkingSpotNumber = parkingSpotNumber;
    this.inTime = Instant.now();
  }

  public String getVehicleRegNumber() {
    return vehicleRegNumber;
  }

  public int getParkingSpotNumber() {
    return parkingSpotNumber;
  }

  public Instant getInTime() {
    return inTime;
  }

  public String getTicketId() {
    return ticketId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ParkingTicket that = (ParkingTicket) o;
    return parkingSpotNumber == that.parkingSpotNumber &&
        ticketId.equals(that.ticketId) &&
        vehicleRegNumber.equals(that.vehicleRegNumber) &&
        inTime.equals(that.inTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ticketId, vehicleRegNumber, parkingSpotNumber, inTime);
  }
}
