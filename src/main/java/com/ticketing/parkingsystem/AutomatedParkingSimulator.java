package com.ticketing.parkingsystem;

import com.ticketing.parkingsystem.controller.ParkingCommandController;
import com.ticketing.parkingsystem.controller.ParkingQueryController;
import com.ticketing.parkingsystem.dao.Dao;
import com.ticketing.parkingsystem.dao.ParkingSystemDaoFactory;
import com.ticketing.parkingsystem.entities.ParkedVehicle;
import com.ticketing.parkingsystem.entities.ParkingSpot;
import com.ticketing.parkingsystem.mapper.ParkingStatusDtoMapper;
import com.ticketing.parkingsystem.service.ParkingCommandService;
import com.ticketing.parkingsystem.service.ParkingQueryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class AutomatedParkingSimulator {

  private Map<String, Consumer<List<String>>> commandMethodMap;

  private ParkingQueryController parkingQueryController;

  private ParkingCommandController parkingCommandController;

  private AutomatedParkingSimulator() {
    Dao<ParkingSpot> parkingSpotDao = ParkingSystemDaoFactory.getParkingSpotDao("local");
    Dao<ParkedVehicle> parkedVehicleDao = ParkingSystemDaoFactory.getParkedVehicleDao("local");

    this.parkingCommandController = new ParkingCommandController(new ParkingCommandService(parkingSpotDao, parkedVehicleDao));
    this.parkingQueryController = new ParkingQueryController(new ParkingQueryService(parkingSpotDao, parkedVehicleDao,
        new ParkingStatusDtoMapper()));

    commandMethodMap = new HashMap<>();
    commandMethodMap.put("park", param -> parkingCommandController.park(param));
    commandMethodMap.put("create_parking_lot", param -> parkingCommandController.createParkingLot(param));
    commandMethodMap.put("leave", param -> parkingCommandController.leave(param));
    commandMethodMap.put("status", param -> parkingQueryController.status(param));
    commandMethodMap.put("registration_numbers_for_cars_with_colour", param -> parkingQueryController.getRegNumbersWithColor(param));
    commandMethodMap.put("slot_numbers_for_cars_with_colour", param -> parkingQueryController.getParkingSpotsWithColor(param));
    commandMethodMap.put("slot_number_for_registration_number", param -> parkingQueryController.getParkingSpotForRegNumber(param));

  }

  public static AutomatedParkingSimulator getInstance() {
    return AutomatedParkingSimulatorHolder.instance;
  }

  public Consumer<List<String>> getMethodForCommand(String command) {
    return commandMethodMap.get(command);
  }

  public void registerNewCommands(String command, Consumer<List<String>> consumer) {
    commandMethodMap.put(command, consumer);
  }

  private static class AutomatedParkingSimulatorHolder {

    static AutomatedParkingSimulator instance = new AutomatedParkingSimulator();
  }
}
