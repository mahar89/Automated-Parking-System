package com.ticketing.parkingsystem.service;

import com.ticketing.parkingsystem.dao.Dao;
import com.ticketing.parkingsystem.dto.ParkingStatusDto;
import com.ticketing.parkingsystem.entities.ParkedVehicle;
import com.ticketing.parkingsystem.entities.ParkingSpot;
import com.ticketing.parkingsystem.mapper.ParkingStatusDtoMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.ticketing.parkingsystem.util.ParkingSystemTestUtil.*;
import static com.ticketing.parkingsystem.util.ParkingSystemTestUtil.getParkedVehiclesStub;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ParkingQueryServiceTest {

  private Dao<ParkingSpot> parkingSpotDao;

  private Dao<ParkedVehicle> parkedVehicleDao;

  private ParkingStatusDtoMapper statusDtoMapper;

  private ParkingQueryService parkingQueryService;

  @Before
  public void setup() {
    parkingSpotDao = mock(Dao.class);
    parkedVehicleDao = mock(Dao.class);
    statusDtoMapper = new ParkingStatusDtoMapper();

    parkingQueryService = new ParkingQueryService(parkingSpotDao, parkedVehicleDao, statusDtoMapper);
  }

  @Test
  public void GIVEN_a_showParkingStatus_request_WHEN_showParkingStatus_invoked_THEN_returns_list_of_occupied_parking_statusDto() {

    List<ParkingSpot> occupiedParkingSpots = occupiedParkingSpotsStub();
    when(parkingSpotDao.getAll()).thenReturn(occupiedParkingSpots);

    List<ParkingStatusDto> parkingStatusDtoList = parkingQueryService.showParkingStatus();
    verify(parkingSpotDao).getAll();

    assertEquals(parkingStatusDtoList.size(), occupiedParkingSpots.size());
    assertTrue(parkingStatusDtoList.containsAll(listOfParkingStatusDtoStub()));
  }

  @Test
  public void GIVEN_a_showParkingStatus_request_WHEN_showParkingStatus_invoked_AND_parkingLot_never_created_THEN_returns_emptyList_of_parkingStatusDto() {

    when(parkingSpotDao.getAll()).thenReturn(Collections.emptyList());

    List<ParkingStatusDto> parkingStatusDtoList = parkingQueryService.showParkingStatus();
    verify(parkingSpotDao).getAll();

    assertTrue(parkingStatusDtoList.isEmpty());
  }

  @Test
  public void GIVEN_a_request_to_get_regNumbers_for_specific_colored_vehicles_WHEN_getVehicleRegNumbersOfVehiclesWithColor_invoked_THEN_returns_list_of_vehiclesRegistrationNumbers() {

    List<ParkedVehicle> parkedVehicles = getParkedVehiclesStub();
    when(parkedVehicleDao.getAll()).thenReturn(parkedVehicles);

    List<String> regNumbers = parkingQueryService.getVehicleRegNumbersOfVehiclesWithColor(VEHICLE_COLOR);
    verify(parkedVehicleDao).getAll();

    assertEquals(regNumbers.size(), parkedVehicles.size());
    assertTrue(regNumbers.containsAll(VEHICLE_REG_NUMBERS));
  }

  @Test
  public void GIVEN_a_request_to_get_regNumbers_for_specific_colored_vehicles_WHEN_getVehicleRegNumbersOfVehiclesWithColor_invoked_AND_no_vehicles_with_given_color_exist_THEN_returns_emptyList_of_vehiclesRegistrationNumbers() {

    List<ParkedVehicle> parkedVehicles = getParkedVehiclesStub();
    when(parkedVehicleDao.getAll()).thenReturn(parkedVehicles);

    List<String> regNumbers = parkingQueryService.getVehicleRegNumbersOfVehiclesWithColor(NON_PARKED_VEHICLE_COLOR);
    verify(parkedVehicleDao).getAll();

    assertTrue(regNumbers.isEmpty());
  }

  @Test
  public void GIVEN_a_request_to_get_parkingSpotNumbers_for_specific_colored_vehicles_WHEN_getParkingSpotNumbersOfVehiclesWithColor_invoked_THEN_returns_list_of_parkingSpotNumbers() {

    List<ParkedVehicle> parkedVehicles = getParkedVehiclesStub();
    when(parkedVehicleDao.getAll()).thenReturn(parkedVehicles);

    List<Integer> spotNumbers = parkingQueryService.getParkingSpotNumbersOfVehiclesWithColor(VEHICLE_COLOR);
    verify(parkedVehicleDao).getAll();

    assertEquals(spotNumbers.size(), parkedVehicles.size());
    assertTrue(spotNumbers.containsAll(VEHICLE_PARKING_SPOT_NUMBERS));
  }

  @Test
  public void GIVEN_a_request_to_get_parkingSpotNumbers_for_specific_colored_vehicles_WHEN_getParkingSpotNumbersOfVehiclesWithColor_invoked_AND_no_vehicles_exist_with_given_color_exist_THEN_returns_empty_list_of_parkingSpotNumbers() {

    List<ParkedVehicle> parkedVehicles = getParkedVehiclesStub();
    when(parkedVehicleDao.getAll()).thenReturn(parkedVehicles);

    List<Integer> spotNumbers = parkingQueryService.getParkingSpotNumbersOfVehiclesWithColor(NON_PARKED_VEHICLE_COLOR);
    verify(parkedVehicleDao).getAll();

    assertTrue(spotNumbers.isEmpty());
  }

  @Test
  public void GIVEN_a_request_to_get_parkingSpotNumber_for_vehicle_WHEN_getParkingSpotNumberForVehicleWithRegNumber_invoked_THEN_returns_the_parkingSpotNumber_of_that_vehicle() {

    ParkedVehicle parkedVehicle = getParkedVehiclesStub().get(0);
    when(parkedVehicleDao.getById(anyString())).thenReturn(Optional.of(parkedVehicle));

    int spotNumber = parkingQueryService.getParkingSpotNumberForVehicleWithRegNumber(VEHICLE_REG_NUMBERS.get(1));
    verify(parkedVehicleDao).getById(anyString());

    assertEquals(spotNumber, parkedVehicle.getParkingTicket().getParkingSpotNumber());
  }


  @Test
  public void GIVEN_a_request_to_get_parkingSpotNumber_for_vehicle_WHEN_getParkingSpotNumberForVehicleWithRegNumber_invoked_AND_no_vehicle_with_given_regNumber_exist_THEN_returns_failed_parkingSpotNumber() {

    when(parkedVehicleDao.getById(anyString())).thenReturn(Optional.empty());

    int spotNumber = parkingQueryService.getParkingSpotNumberForVehicleWithRegNumber(VEHICLE_REG_NUMBER);
    verify(parkedVehicleDao).getById(anyString());

    assertEquals(spotNumber, FAILED_PARKING_SPOT);
  }


}
