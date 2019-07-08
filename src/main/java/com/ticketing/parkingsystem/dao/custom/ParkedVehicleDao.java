package com.ticketing.parkingsystem.dao.custom;

import com.ticketing.parkingsystem.dao.Dao;
import com.ticketing.parkingsystem.entities.ParkedVehicle;

import java.util.*;

public class ParkedVehicleDao implements Dao<ParkedVehicle> {

  private Map<String, ParkedVehicle> idToParkedVehicleMap = new HashMap<>();

  @Override
  public Optional<ParkedVehicle> getById(String id) {
    return Optional.ofNullable(idToParkedVehicleMap.get(id));
  }

  @Override
  public List<ParkedVehicle> getAll() {
    Collection<ParkedVehicle> parkedVehicleCollection = idToParkedVehicleMap.values();

    return new ArrayList<>(parkedVehicleCollection);
  }

  @Override
  public ParkedVehicle upsert(ParkedVehicle parkedVehicle) {
    return idToParkedVehicleMap.put(parkedVehicle.getRegNumber(), parkedVehicle);
  }

  @Override
  public boolean delete(ParkedVehicle parkedVehicle) {
    return null != idToParkedVehicleMap.remove(parkedVehicle.getRegNumber());
  }
}