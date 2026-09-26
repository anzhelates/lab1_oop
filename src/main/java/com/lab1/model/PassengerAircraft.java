package com.lab1.model;

public abstract class PassengerAircraft extends Aircraft {
    private final int seatCapacity;
    private final double luggageCapacityKg;

    public PassengerAircraft(String model, String manufacturer, double maxRangeKm,
                             double fuelConsumptionPerHour, double cruiseSpeedKmh,
                             int seatCapacity, double luggageCapacityKg) {
        super(model, manufacturer, maxRangeKm, fuelConsumptionPerHour, cruiseSpeedKmh);

        this.seatCapacity = seatCapacity;
        this.luggageCapacityKg = luggageCapacityKg;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public double getLuggageCapacityKg() {
        return luggageCapacityKg;
    }

    @Override
    public String getPayload() {
        return String.format("Passenger capacity: %d seats, luggage: %.0f kg",
                seatCapacity, luggageCapacityKg);
    }
}