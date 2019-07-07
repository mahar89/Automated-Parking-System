package com.ticketing.parkingsystem.service;

import com.ticketing.parkingsystem.dao.Dao;
import com.ticketing.parkingsystem.dto.ParkingStatusDto;
import com.ticketing.parkingsystem.entities.ParkedVehicle;
import com.ticketing.parkingsystem.entities.ParkingSpot;
import com.ticketing.parkingsystem.mapper.ParkingStatusDtoMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ParkingQueryService {

  private final Dao<ParkingSpot> parkingSpotDao;

  private final Dao<ParkedVehicle> parkedVehicleDao;

  private final ParkingStatusDtoMapper parkingStatusDtoMapper;

  public ParkingQueryService(Dao<ParkingSpot> parkingSpotDao,
                             Dao<ParkedVehicle> parkedVehicleDao,
                             ParkingStatusDtoMapper parkingStatusDtoMapper) {
    this.parkingSpotDao = parkingSpotDao;
    this.parkedVehicleDao = parkedVehicleDao;
    this.parkingStatusDtoMapper = parkingStatusDtoMapper;

  }

  public List<ParkingStatusDto> showParkingStatus() {
    List<ParkingSpot> parkingSpots = parkingSpotDao.getAll();

    List<ParkingSpot> occupiedParkingSpots = parkingSpots.stream()
        .filter(parkingSpot -> !parkingSpot.isFree())
        .collect(Collectors.toList());

    return occupiedParkingSpots.stream()
        .map(ps -> parkingStatusDtoMapper.parkingSpotToParkingStatusDto(ps))
        .collect(Collectors.toList());
  }

  public List<String> getVehicleRegNumbersOfVehiclesWithColor(String vehicleColor) {
    List<ParkedVehicle> parkedVehicles = parkedVehicleDao.getAll();

    return parkedVehicles.stream()
        .filter(parkedVehicle -> parkedVehicle.getColor().equals(vehicleColor))
        .map(ParkedVehicle::getRegNumber)
        .collect(Collectors.toList());
  }

  public List<Integer> getParkingSpotNumbersOfVehiclesWithColor(String vehicleColor) {
    List<ParkedVehicle> parkedVehicles = parkedVehicleDao.getAll();

    return parkedVehicles.stream()
        .filter(parkedVehicle -> parkedVehicle.getColor().equals(vehicleColor))
        .collect(Collectors.toList())
        .stream()
        .map(parkedVehicle -> parkedVehicle.getParkingTicket().getParkingSpotNumber())
        .collect(Collectors.toList());
  }

  public int getParkingSpotNumberForVehicleWithRegNumber(String vehicleRegNumber) {
    Optional<ParkedVehicle> parkedVehicleOptional = parkedVehicleDao.getById(vehicleRegNumber);

    return parkedVehicleOptional.isPresent() ?
        parkedVehicleOptional.get().getParkingTicket().getParkingSpotNumber() : -1;
  }
}
