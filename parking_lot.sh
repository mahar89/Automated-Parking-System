#!/bin/bash

mvn clean install
java -cp target/Automated-Parking-System-1.0-SNAPSHOT.jar com.ticketing.parkingsystem.ParkingSystemApp $1