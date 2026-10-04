package com.lab1.company;

import com.lab1.model.Aircraft;
import com.lab1.model.CargoAircraft;
import com.lab1.model.PassengerAircraft;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Airline {

    private final String name;
    private final List<Aircraft> fleet = new ArrayList<>();

    public Airline(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public String getName() { return name; }

    public List<Aircraft> getFleet() { return new ArrayList<>(fleet); }

    public void addAircraft(Aircraft aircraft) {
        if (aircraft == null) {
            throw new IllegalArgumentException("Aircraft cannot be null");
        }
        for (Aircraft existing : fleet) {
            if (existing.getRegistration().equals(
                    aircraft.getRegistration())) {
                throw new IllegalArgumentException(
                        "Registration already in fleet: "
                                + aircraft.getRegistration());
            }
        }
        fleet.add(aircraft);
    }

    public boolean removeAircraft(Aircraft aircraft) {
        return fleet.remove(aircraft);
    }

    public int calculateTotalPassengerCapacity() {
        int total = 0;
        for (Aircraft aircraft : fleet) {
            if (aircraft instanceof PassengerAircraft passenger) {
                total += passenger.getPassengerCapacity();
            }
        }
        return total;
    }

    public double calculateTotalCargoCapacityKg() {
        double total = 0;
        for (Aircraft aircraft : fleet) {
            if (aircraft instanceof CargoAircraft cargo) {
                total += cargo.getCargoCapacityKg();
            }
        }
        return total;
    }

    public List<Aircraft> getFleetSortedByRange() {
        List<Aircraft> sorted = getFleet();
        sorted.sort(Comparator.comparingDouble(Aircraft::getMaxRangeKm));
        return sorted;
    }

    public List<Aircraft> findByFuelConsumptionRange(double minKgPerHour,
                                                     double maxKgPerHour) {
        if (minKgPerHour < 0 || maxKgPerHour < minKgPerHour) {
            throw new IllegalArgumentException("Invalid fuel range");
        }
        List<Aircraft> result = new ArrayList<>();
        for (Aircraft aircraft : fleet) {
            double fuel = aircraft.getFuelPerHour();
            if (fuel >= minKgPerHour && fuel <= maxKgPerHour) {
                result.add(aircraft);
            }
        }
        return result;
    }

    public List<Aircraft> findAircraftForRoute(double distanceKm) {
        if (distanceKm <= 0) {
            throw new IllegalArgumentException(
                    "Distance must be positive");
        }
        List<Aircraft> result = new ArrayList<>();
        for (Aircraft aircraft : fleet) {
            if (aircraft.canFly(distanceKm)) {
                result.add(aircraft);
            }
        }
        return result;
    }
}