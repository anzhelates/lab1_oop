package com.lab1.model;

public abstract class Aircraft {
    private final String registration;
    private final String model;
    private final String manufacturer;
    private final double maxRangeKm;
    private final double fuelPerHour;

    protected Aircraft(String registration, String manufacturer,
                       String model, double maxRangeKm, double fuelPerHour) {
        if (isBlank(registration) || isBlank(manufacturer)
                || isBlank(model)) {
            throw new IllegalArgumentException(
                    "Registration, manufacturer and model are required");
        }
        if (!(maxRangeKm > 0 && fuelPerHour > 0)) {
            throw new IllegalArgumentException(
                    "Range and fuel burn must be positive");
        }
        this.registration = registration.trim();
        this.manufacturer = manufacturer.trim();
        this.model = model.trim();
        this.maxRangeKm = maxRangeKm;
        this.fuelPerHour = fuelPerHour;
    }

    public abstract int getPassengerCapacity();

    public abstract double getCargoCapacityKg();

    public boolean canFly(double distanceKm) {
        return distanceKm > 0 && distanceKm <= maxRangeKm;
    }

    public String getDisplayName() {
        return manufacturer + " " + model;
    }

    public String getRegistration() {
        return registration;
    }

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

    @Override
    public String toString() {
        return registration + " (" + getDisplayName() + ")";
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}