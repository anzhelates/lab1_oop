package com.lab1.company;

import com.lab1.model.Aircraft;
import com.lab1.model.CargoAircraft;
import com.lab1.model.PassengerAircraft;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Airline {

    private final String name;
    private final List<Aircraft> fleet;

    public Airline(String name) {
        this.name = name;
        this.fleet = new ArrayList<>();
    }

    public String getName() { return name; }

    public List<Aircraft> getFleet() { return new ArrayList<>(fleet); }

    public void addAircraft(Aircraft aircraft) {
        if (aircraft == null) {
            throw new IllegalArgumentException("Aircraft cannot be null");
        }
        this.fleet.add(aircraft);
    }

    public void removeAircraft(Aircraft aircraft) {
        this.fleet.remove(aircraft);
    }

    public int calculateTotalPassengerCapacity() {
        int totalPassengerCapacity = 0;
        for (Aircraft aircraft : fleet) {
            if (aircraft instanceof PassengerAircraft passengerAircraft) {
                totalPassengerCapacity += passengerAircraft.getSeatCapacity();
            }
        }
        return totalPassengerCapacity;
    }

    public int calculateTotalCargoCapacityTons() {
        int totalCargoCapacityTons = 0;
        for (Aircraft aircraft : fleet) {
            if (aircraft instanceof CargoAircraft cargoAircraft) {
                totalCargoCapacityTons += cargoAircraft.getMaxPayloadTons();
            }
        }
        return totalCargoCapacityTons;
    }

    public void sortByRange() {
        fleet.sort(Comparator.comparingDouble(Aircraft::getMaxRangeKm));
    }

    public List<Aircraft> findByFuelConsumptionRange(double minFuelPerHour,
                                                     double maxFuelPerHour) {
        if (minFuelPerHour < 0 || maxFuelPerHour < minFuelPerHour) {
            throw new IllegalArgumentException("Invalid range");
        }

        List<Aircraft> result = new ArrayList<>();
        for (Aircraft aircraft : fleet) {
            double fuel = aircraft.getFuelPerHour();
            if (fuel >= minFuelPerHour && fuel <= maxFuelPerHour) {
                result.add(aircraft);
            }
        }
        return result;
    }

    public void printFleet() {
        System.out.println("Airline: " + name);
        System.out.println("Total passenger capacity: "
                + calculateTotalPassengerCapacity() + " seats");
        System.out.println("Total cargo capacity: "
                + calculateTotalCargoCapacityTons() + " tons");

        for (int i = 0; i < fleet.size(); i++) {
            System.out.printf("%2d. %s%n", i + 1, fleet.get(i).toString());
        }
    }
}