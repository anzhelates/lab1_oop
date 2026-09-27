package com.lab1.model;

public class WideBodyJet extends PassengerAircraft {
    private final boolean hasCrewRestCompartment;

    public WideBodyJet(String model, String manufacturer,
                       double maxRangeKm, double fuelConsumptionPerHour,
                       double cruiseSpeedKmh, int seatCapacity,
                       double luggageCapacityKg, boolean hasCrewRestCompartment) {
        super(model, manufacturer, maxRangeKm, fuelConsumptionPerHour,
                cruiseSpeedKmh, seatCapacity, luggageCapacityKg);

        this.hasCrewRestCompartment = hasCrewRestCompartment;
    }

    public boolean hasCrewRestCompartment() {
        return hasCrewRestCompartment;
    }
}