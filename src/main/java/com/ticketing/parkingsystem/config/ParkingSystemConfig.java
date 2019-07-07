package com.ticketing.parkingsystem.config;

public class ParkingSystemConfig {

  public static int getMaxAllowedParkingLotSize() {

    return Integer.parseInt(System.getProperty("maxLotCapacity","1000"));
  }
}
