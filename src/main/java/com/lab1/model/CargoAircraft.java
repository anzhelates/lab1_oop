package com.lab1.model;

public class CargoAircraft extends Aircraft {
    private final double cargoCapacityKg;

    public CargoAircraft(String registration, String manufacturer,
                         String model, double maxRangeKm,
                         double fuelPerHour, double cargoCapacityKg) {
        super(registration, manufacturer, model, maxRangeKm,
                fuelPerHour);
        if (!(cargoCapacityKg > 0)) {
            throw new IllegalArgumentException(
                    "Cargo capacity must be positive");
        }
        this.cargoCapacityKg = cargoCapacityKg;
    }

    public double getCargoCapacityKg() {
        return cargoCapacityKg;
    }
}