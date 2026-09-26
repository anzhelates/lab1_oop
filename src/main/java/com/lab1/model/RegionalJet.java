package com.lab1.model;

public class RegionalJet extends PassengerAircraft {
    private final double maxTakeoffWeightKg;

    public RegionalJet(String model, String manufacturer, double maxRangeKm,
                       double fuelConsumptionPerHour, double cruiseSpeedKmh,
                       int seatCapacity, double luggageCapacityKg, double maxTakeoffWeightKg) {
        super(model, manufacturer, maxRangeKm, fuelConsumptionPerHour, cruiseSpeedKmh, seatCapacity, luggageCapacityKg);

        this.maxTakeoffWeightKg = maxTakeoffWeightKg;
    }

    public double getMaxTakeoffWeightKg() {
        return maxTakeoffWeightKg;
    }
}