package com.lab1.model;

public class CargoAircraft extends Aircraft {
    private final int maxPayloadTons;
    private final double cargoVolumeM3;

    public CargoAircraft(String model, String manufacturer,
                         double maxRangeKm, double fuelConsumptionPerHour,
                         double cruiseSpeedKmh, int maxPayloadTons,
                         double cargoVolumeM3) {
        super(model, manufacturer, maxRangeKm, fuelConsumptionPerHour,
                cruiseSpeedKmh);

        this.maxPayloadTons = maxPayloadTons;
        this.cargoVolumeM3 = cargoVolumeM3;
    }

    public int getMaxPayloadTons() {
        return maxPayloadTons;
    }

    public double getCargoVolumeM3() {
        return cargoVolumeM3;
    }

    @Override
    public String getPayload() {
        return String.format("Max payload: %d tons, cargo volume: %.0f m3",
                maxPayloadTons, cargoVolumeM3);
    }
}