package com.ticketing.parkingsystem.dao.custom;

import com.ticketing.parkingsystem.dao.Dao;
import com.ticketing.parkingsystem.entities.ParkingSpot;

import java.util.*;

public class ParkingSpotDao implements Dao<ParkingSpot> {

  private Map<Integer, ParkingSpot> idToParkingSpotMap = new HashMap<>();

  @Override
  public Optional<ParkingSpot> getById(String spotNumber) {
    return Optional.ofNullable(idToParkingSpotMap.get(spotNumber));
  }

  @Override
  public List<ParkingSpot> getAll() {
    Collection<ParkingSpot> parkingSpotCollection = idToParkingSpotMap.values();

    return new ArrayList<>(parkingSpotCollection);
  }

  @Override
  public ParkingSpot upsert(ParkingSpot parkingSpot) {
    return idToParkingSpotMap.put(parkingSpot.getSpotNumber(), parkingSpot);
  }

  @Override
  public boolean delete(ParkingSpot parkingSpot) {

    return null != idToParkingSpotMap.remove(parkingSpot.getSpotNumber());
  }
}
