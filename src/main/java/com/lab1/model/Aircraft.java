package com.lab1.model;

public abstract class Aircraft {
    private final String model;
    private final String manufacturer;
    private final double maxRangeKm;
    private final double fuelPerHour;
    private final double cruiseSpeedKmh;

    public Aircraft(String model, String manufacturer, double maxRangeKm,
                    double fuelPerHour, double cruiseSpeedKmh) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.maxRangeKm = maxRangeKm;
        this.fuelPerHour = fuelPerHour;
        this.cruiseSpeedKmh = cruiseSpeedKmh;
    }

    public abstract String getPayload();

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getMaxRangeKm() {
        return maxRangeKm;
    }

    public double getFuelPerHour() {
        return fuelPerHour;
    }

    public double getCruiseSpeedKmh() {
        return cruiseSpeedKmh;
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s: range: %.0f km, fuel: %.1f l/h, speed: %.0f km/h - %s",
                manufacturer, model, maxRangeKm, fuelPerHour, cruiseSpeedKmh,
                getPayload());
    }
}