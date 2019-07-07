package com.ticketing.parkingsystem.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static com.ticketing.parkingsystem.util.ParkingSystemTestUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ParkingCommandServiceTest {

  @InjectMocks
  private ParkingCommandService parkingCommandService;

  @Test
  public void GIVEN_a_valid_parkingLot_size_AND_parkingLot_never_created_WHEN_createParkingLot_invoked_THEN_parkingLot_is_created() {

    assertTrue(parkingCommandService.createParkingLot(VALID_PARKING_SIZE));
  }

  @Test
  public void GIVEN_an_invalid_parkingLot_size_AND_parkingLot_never_created_WHEN_createParkingLot_invoked_THEN_parkingLot_is_not_created() {

    assertTrue(parkingCommandService.createParkingLot(INVALID_PARKING_SIZE));
  }


  @Test
  public void GIVEN_a_parkingLot_size_exceeding_permissible_number_WHEN_createParkingLot_invoked_THEN_parkingLot_is_not_created() {

    assertTrue(parkingCommandService.createParkingLot(VALID_PARKING_SIZE));
  }

  @Test
  public void GIVEN_a_valid_parkingLot_size_AND_parkingLot_already_created_WHEN_createParkingLot_invoked_THEN_parkingLot_is_not_created() {

    assertTrue(parkingCommandService.createParkingLot(INVALID_PARKING_SIZE));
  }

  @Test
  public void GIVEN_a_new_park_vehicle_request_AND_parking_is_available_WHEN_parkVehicle_invoked_THEN_returns_parkingSpotNo() {

    assertTrue(parkingCommandService.parkVehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR) >= VALID_PARKING_SIZE);
  }

  @Test
  public void GIVEN_a_new_park_vehicle_request_AND_parking_is_not_available_WHEN_parkVehicle_invoked_THEN_returns_failed_parkingSpotNo() {

    assertEquals(parkingCommandService.parkVehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR), VALID_PARKING_SIZE);
  }

  @Test
  public void GIVEN_a_new_park_vehicle_request_WHEN_required_fields_are_missing_AND_parkVehicle_invoked_THEN_returns_failed_parkingSpotNo() {

    assertEquals(parkingCommandService.parkVehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR), VALID_PARKING_SIZE);
  }

  @Test
  public void GIVEN_multiple_park_vehicle_request_AND_parkings_are_available_WHEN_each_time_parkVehicle_invoked_THEN_returns_unique_parkingSpotNo() {
    for (int i =1; i<= VALID_PARKING_SIZE; i++) {
      assertEquals(parkingCommandService.parkVehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR), VALID_PARKING_SIZE);
    }
  }

  @Test
  public void GIVEN_an_exit_parking_spot_request_WHEN_leaveParkingSpot_invoked_THEN_returns_parkingSpot_is_freed() {

    assertTrue(parkingCommandService.leaveParkingSpot(VALID_PARKING_SPOT));
  }

  @Test
  public void GIVEN_an_exit_parking_spot_request_AND_parkingSpotNumber_does_not_exist_WHEN_leaveParkingSpot_invoked_THEN_returns_parkingSpot_is_not_freed() {

    assertTrue(parkingCommandService.leaveParkingSpot(INVALID_PARKING_SPOT));
  }

}
