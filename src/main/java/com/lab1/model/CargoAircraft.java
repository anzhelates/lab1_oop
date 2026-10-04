package com.lab1.model;

public class CargoAircraft extends Aircraft {

    public CargoAircraft(String registration, String manufacturer,
                         String model, double maxRangeKm, double fuelPerHour,
                         double cargoCapacityKg) {
        super(registration, manufacturer, model, maxRangeKm,
                fuelPerHour, cargoCapacityKg);
        if (!(cargoCapacityKg > 0)) {
            throw new IllegalArgumentException("Cargo capacity must be positive");
        }
    }

    @Override
    public int getPassengerCapacity() {
        return 0;
    }
}