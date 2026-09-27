package com.lab1;

import com.lab1.company.Airline;
import com.lab1.io.AircraftFileManager;
import com.lab1.model.Aircraft;
import com.lab1.io.Constants;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AircraftFileManager fileManager = new AircraftFileManager();

        try {
            List<Aircraft> loadedFleet = fileManager.loadFleet(
                    Constants.DATA_FOLDER + "/aircraft_data" + Constants.FILE_EXTENSION);

            Airline airline = new Airline("SkyLine Airways");
            for (Aircraft aircraft : loadedFleet) {
                airline.addAircraft(aircraft);
            }

            airline.printFleet();

            System.out.println("\nSorted by range:");
            airline.sortByRange();
            airline.printFleet();

            System.out.println("\nAircraft with fuel consumption between 5000 and 7000 l/h:");
            List<Aircraft> matches = airline.findByFuelConsumptionRange(5000, 7000);
            for (Aircraft aircraft : matches) {
                System.out.println(aircraft);
            }

        } catch (IOException e) {
            System.err.println("Failed to load aircraft data: " + e.getMessage());
        }
    }
}