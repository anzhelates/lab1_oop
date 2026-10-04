package com.lab1.model;

public abstract class PassengerAircraft extends Aircraft {
    private final int seatCapacity;
    private final double cargoHoldKg;

    public PassengerAircraft(String registration, String manufacturer,
                             String model, double maxRangeKm, double fuelBurnKgPerHour,
                             int seatCapacity, double cargoHoldKg) {
        super(registration, manufacturer, model, maxRangeKm,
                fuelBurnKgPerHour);
        if (seatCapacity <= 0 || cargoHoldKg < 0) {
            throw new IllegalArgumentException(
                    "Seats must be positive and cargo hold cannot be "
                            + "negative");
        }
        this.seatCapacity = seatCapacity;
        this.cargoHoldKg = cargoHoldKg;
    }

    @Override
    public int getPassengerCapacity() {
        return seatCapacity;
    }

    @Override
    public double getCargoCapacityKg() {
        return cargoHoldKg;
    }
}