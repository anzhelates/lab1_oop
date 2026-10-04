package com.lab1.model;

public class CargoAircraft extends Aircraft {
    private final double maxPayloadKg;

    public CargoAircraft(String registration, String manufacturer,
                         String model, double maxRangeKm, double fuelBurnKgPerHour,
                         double maxPayloadKg) {
        super(registration, manufacturer, model, maxRangeKm,
                fuelBurnKgPerHour);
        if (!(maxPayloadKg > 0)) {
            throw new IllegalArgumentException("Payload must be positive");
        }
        this.maxPayloadKg = maxPayloadKg;
    }

    @Override
    public int getPassengerCapacity() {
        return 0;
    }

    @Override
    public double getCargoCapacityKg() {
        return maxPayloadKg;
    }
}