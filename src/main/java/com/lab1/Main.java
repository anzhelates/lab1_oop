package com.lab1;

import com.lab1.company.Airline;
import com.lab1.io.AircraftLoader;
import com.lab1.model.Aircraft;

import java.io.IOException;
import java.util.List;

public class Main {

    private static final String AIRLINE_NAME = "SkyLine Airways";
    private static final String FLEET_RESOURCE = "/fleet.txt";
    private static final double MIN_FUEL_KG_PER_HOUR = 5000;
    private static final double MAX_FUEL_KG_PER_HOUR = 7000;
    private static final double LONG_ROUTE_KM = 14000;

    public static void main(String[] args) throws IOException {
        Airline airline = new Airline(AIRLINE_NAME);
        for (Aircraft aircraft
                : new AircraftLoader().loadFromResource(FLEET_RESOURCE)) {
            airline.addAircraft(aircraft);
        }

        System.out.println("Airline: " + airline.getName());
        System.out.println("Total passenger capacity: "
                + airline.calculateTotalPassengerCapacity() + " seats");
        System.out.printf("Total cargo capacity: %.1f tons%n",
                airline.calculateTotalCargoCapacityKg() / 1000);

        printFleet("Fleet:", airline.getFleet());
        printFleet("Sorted by range:", airline.getFleetSortedByRange());
        printFleet(String.format("Fuel consumption %.0f-%.0f kg/h:",
                        MIN_FUEL_KG_PER_HOUR, MAX_FUEL_KG_PER_HOUR),
                airline.findByFuelConsumptionRange(MIN_FUEL_KG_PER_HOUR,
                        MAX_FUEL_KG_PER_HOUR));
        printFleet(String.format("Able to fly %.0f km:", LONG_ROUTE_KM),
                airline.findAircraftForRoute(LONG_ROUTE_KM));
    }

    private static void printFleet(String title, List<Aircraft> fleet) {
        System.out.println();
        System.out.println(title);
        for (Aircraft aircraft : fleet) {
            System.out.printf(
                    "  %-7s %-18s range %5.0f km, fuel %4.0f kg/h,"
                            + " seats %3d, cargo %6.0f kg%n",
                    aircraft.getRegistration(),
                    aircraft.getDisplayName(),
                    aircraft.getMaxRangeKm(),
                    aircraft.getFuelPerHour(),
                    aircraft.getPassengerCapacity(),
                    aircraft.getCargoCapacityKg());
        }
    }
}