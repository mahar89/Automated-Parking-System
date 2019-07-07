package com.ticketing.parkingsystem.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static com.ticketing.parkingsystem.util.ParkingSystemTestUtil.*;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ParkingQueryServiceTest {

  @InjectMocks
  private ParkingQueryService parkingQueryService;

  @Test
  public void GIVEN_a_showParkingStatus_request_WHEN_showParkingStatus_invoked_THEN_returns_list_of_parkingStatusDto() {

    assertEquals(parkingQueryService.showParkingStatus(), null);
  }

  @Test
  public void GIVEN_a_showParkingStatus_request_WHEN_showParkingStatus_invoked_AND_parkingLot_never_created_THEN_returns_emptyList_of_parkingStatusDto() {

    assertEquals(parkingQueryService.showParkingStatus(), null);
  }

  @Test
  public void GIVEN_a_request_to_get_regNumbers_for_specific_colored_vehicles_WHEN_getVehicleRegNumbersOfVehiclesWithColor_invoked_THEN_returns_list_of_vehiclesRegistrationNumbers() {

    assertEquals(parkingQueryService.getVehicleRegNumbersOfVehiclesWithColor(VEHICLE_COLOR), null);
  }
  
  @Test
  public void GIVEN_a_request_to_get_regNumbers_for_specific_colored_vehicles_WHEN_getVehicleRegNumbersOfVehiclesWithColor_invoked_AND_no_vehicles_with_given_color_exist_THEN_returns_emptyList_of_vehiclesRegistrationNumbers() {

    assertEquals(parkingQueryService.getVehicleRegNumbersOfVehiclesWithColor(VEHICLE_COLOR), null);
  }
  
  @Test
  public void GIVEN_a_request_to_get_parkingSpotNumbers_for_specific_colored_vehicles_WHEN_getParkingSpotNumbersOfVehiclesWithColor_invoked_THEN_returns_list_of_parkingSpotNumbers() {

    assertEquals(parkingQueryService.getParkingSpotNumbersOfVehiclesWithColor(VEHICLE_COLOR), null);
  }

  @Test
  public void GIVEN_a_request_to_get_parkingSpotNumbers_for_specific_colored_vehicles_WHEN_getParkingSpotNumbersOfVehiclesWithColor_invoked_AND_no_vehicles_exist_with_given_color_exist_THEN_returns_empty_list_of_parkingSpotNumbers() {

    assertEquals(parkingQueryService.getParkingSpotNumbersOfVehiclesWithColor(VEHICLE_COLOR), null);
  }


  @Test
  public void GIVEN_a_request_to_get_parkingSpotNumber_for_vehicle_WHEN_getParkingSpotNumberForVehicleWithRegNumber_invoked_THEN_returns_the_parkingSpotNumber_of_that_vehicle() {

    assertEquals(parkingQueryService.getParkingSpotNumberForVehicleWithRegNumber(VEHICLE_REG_NUMBER), null);
  }


  @Test
  public void GIVEN_a_request_to_get_parkingSpotNumber_for_vehicle_WHEN_getParkingSpotNumberForVehicleWithRegNumber_invoked_AND_no_vehicle_with_given_regNumber_exist_THEN_returns_failed_parkingSpotNumber() {

    assertEquals(parkingQueryService.getParkingSpotNumberForVehicleWithRegNumber(VEHICLE_REG_NUMBER), null);
  }
  
  
}
