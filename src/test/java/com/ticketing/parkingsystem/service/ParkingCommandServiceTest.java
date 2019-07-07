package com.ticketing.parkingsystem.service;

import com.ticketing.parkingsystem.dao.Dao;
import com.ticketing.parkingsystem.entities.ParkedVehicle;
import com.ticketing.parkingsystem.entities.ParkingSpot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static com.ticketing.parkingsystem.util.ParkingSystemTestUtil.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ParkingCommandServiceTest {

  private Dao<ParkingSpot> parkingSpotDao;

  private Dao<ParkedVehicle> parkedVehicleDao;

  private ParkingCommandService parkingCommandService;

  @Before
  public void setup() {
    parkingSpotDao = mock(Dao.class);
    parkedVehicleDao = mock(Dao.class);

    parkingCommandService = new ParkingCommandService(parkingSpotDao, parkedVehicleDao);
  }

  @Test
  public void GIVEN_a_valid_parkingLot_size_AND_parkingLot_never_created_WHEN_createParkingLot_invoked_THEN_parkingLot_is_created() {
    when(parkingSpotDao.getAll()).thenReturn(Collections.emptyList());

    boolean isCreated = parkingCommandService.createParkingLot(VALID_PARKING_SIZE);
    verify(parkingSpotDao).getAll();
    verify(parkingSpotDao, times(VALID_PARKING_SIZE)).upsert(any(ParkingSpot.class));

    assertTrue(isCreated);
  }

  @Test
  public void GIVEN_an_invalid_parkingLot_size_AND_parkingLot_never_created_WHEN_createParkingLot_invoked_THEN_parkingLot_is_not_created() {
    when(parkingSpotDao.getAll()).thenReturn(Collections.emptyList());

    boolean isCreated = parkingCommandService.createParkingLot(INVALID_PARKING_SIZE);
    verify(parkingSpotDao).getAll();
    verify(parkingSpotDao, times(0)).upsert(any(ParkingSpot.class));

    assertFalse(isCreated);
  }


  @Test
  public void GIVEN_a_parkingLot_size_exceeding_permissible_number_WHEN_createParkingLot_invoked_THEN_parkingLot_is_not_created() {
    when(parkingSpotDao.getAll()).thenReturn(Collections.emptyList());

    boolean isCreated = parkingCommandService.createParkingLot(PARKING_SIZE_MAX);
    verify(parkingSpotDao).getAll();
    verify(parkingSpotDao, times(0)).upsert(any(ParkingSpot.class));

    assertFalse(isCreated);
  }

  @Test
  public void GIVEN_a_valid_parkingLot_size_AND_parkingLot_already_created_WHEN_createParkingLot_invoked_THEN_parkingLot_is_not_created() {

    when(parkingSpotDao.getAll()).thenReturn(createParkingSpotsStub(VALID_PARKING_SIZE, false));

    boolean isCreated = parkingCommandService.createParkingLot(VALID_PARKING_SIZE);
    verify(parkingSpotDao).getAll();
    verify(parkingSpotDao, times(0)).upsert(any(ParkingSpot.class));

    assertFalse(isCreated);
  }

  @Test
  public void GIVEN_a_new_park_vehicle_request_AND_parking_is_available_WHEN_parkVehicle_invoked_THEN_returns_parkingSpotNumber_nearest_to_entry() {

    when(parkingSpotDao.getAll()).thenReturn(createParkingSpotsStub(VALID_PARKING_SIZE, false));

    int parkingSpotNumber = parkingCommandService.parkVehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR);
    verify(parkingSpotDao).getAll();
    verify(parkingSpotDao, times(1)).upsert(any(ParkingSpot.class));
    verify(parkedVehicleDao, times(1)).upsert(any(ParkedVehicle.class));

    assertEquals(parkingSpotNumber, NEAREST_PARKING_SPOT);
  }

  @Test
  public void GIVEN_a_new_park_vehicle_request_AND_parking_is_full_WHEN_parkVehicle_invoked_THEN_returns_failed_parkingSpotNo() {

    when(parkingSpotDao.getAll()).thenReturn(createParkingSpotsStub(VALID_PARKING_SIZE, true));

    int parkingSpotNumber = parkingCommandService.parkVehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR);
    verify(parkingSpotDao).getAll();
    verify(parkingSpotDao, times(0)).upsert(any(ParkingSpot.class));
    verify(parkedVehicleDao, times(0)).upsert(any(ParkedVehicle.class));

    assertEquals(parkingSpotNumber, FAILED_PARKING_SPOT);
  }

  @Test
  public void GIVEN_a_new_park_vehicle_request_WHEN_required_fields_are_missing_AND_parkVehicle_invoked_THEN_returns_failed_parkingSpotNo() {

    when(parkingSpotDao.getAll()).thenReturn(createParkingSpotsStub(VALID_PARKING_SIZE, false));

    int parkingSpotNumber = parkingCommandService.parkVehicle(null, null);
    verify(parkingSpotDao, times(0)).getAll();
    verify(parkingSpotDao, times(0)).upsert(any(ParkingSpot.class));
    verify(parkedVehicleDao, times(0)).upsert(any(ParkedVehicle.class));

    assertEquals(parkingSpotNumber, FAILED_PARKING_SPOT);
  }


  @Test
  public void GIVEN_an_exit_parking_spot_request_WHEN_leaveParkingSpot_invoked_THEN_parkingSpot_is_freed() {
    when(parkingSpotDao.getById(anyString())).thenReturn(createParkingSpotStub(VALID_PARKING_SPOT, true));

    boolean freed = parkingCommandService.leaveParkingSpot(VALID_PARKING_SPOT);
    verify(parkingSpotDao).getById(anyString());
    verify(parkingSpotDao, times(1)).upsert(any(ParkingSpot.class));
    verify(parkedVehicleDao, times(1)).delete(any(ParkedVehicle.class));

    assertTrue(freed);
  }

  @Test
  public void GIVEN_an_exit_parking_spot_request_AND_parkingSpotNumber_does_not_exist_WHEN_leaveParkingSpot_invoked_THEN_parkingSpot_is_not_freed() {

    when(parkingSpotDao.getById(anyString())).thenReturn(Optional.empty());

    boolean freed = parkingCommandService.leaveParkingSpot(INVALID_PARKING_SPOT);
    verify(parkingSpotDao).getById(anyString());
    verify(parkingSpotDao, times(0)).upsert(any(ParkingSpot.class));
    verify(parkedVehicleDao, times(0)).delete(any(ParkedVehicle.class));

    assertFalse(freed);
  }
}
