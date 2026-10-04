package com.lab1.model;

public class PassengerAircraft extends Aircraft {
    private final int seatCapacity;

    public PassengerAircraft(String registration, String manufacturer,
                             String model, double maxRangeKm,
                             double fuelPerHour, int seatCapacity,
                             double cargoCapacityKg) {
        super(registration, manufacturer, model, maxRangeKm,
                fuelPerHour, cargoCapacityKg);
        if (seatCapacity <= 0) {
            throw new IllegalArgumentException("Seats must be positive");
        }
        this.seatCapacity = seatCapacity;
    }

    @Override
    public int getPassengerCapacity() {
        return seatCapacity;
    }
}