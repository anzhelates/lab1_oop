package com.lab1.io;

import com.lab1.model.Aircraft;
import com.lab1.model.CargoAircraft;
import com.lab1.model.PassengerAircraft;

import java.util.Locale;

public final class AircraftFactory {

    private static final String TYPE_PASSENGER = "PASSENGER";
    private static final String TYPE_CARGO = "CARGO";
    private static final int PASSENGER_FIELDS = 8;
    private static final int CARGO_FIELDS = 7;

    private AircraftFactory() {
    }

    public static Aircraft createAircraft(String[] fields) {
        return switch (fields[0].toUpperCase(Locale.ROOT)) {
            case TYPE_PASSENGER -> createPassenger(fields);
            case TYPE_CARGO -> createCargo(fields);
            default -> throw new IllegalArgumentException(
                    "Unknown aircraft type: " + fields[0]);
        };
    }

    private static Aircraft createPassenger(String[] f) {
        requireFields(f, PASSENGER_FIELDS);
        return new PassengerAircraft(f[1], f[2], f[3],
                Double.parseDouble(f[4]), Double.parseDouble(f[5]),
                Integer.parseInt(f[6]), Double.parseDouble(f[7]));
    }

    private static Aircraft createCargo(String[] f) {
        requireFields(f, CARGO_FIELDS);
        return new CargoAircraft(f[1], f[2], f[3],
                Double.parseDouble(f[4]), Double.parseDouble(f[5]),
                Double.parseDouble(f[6]));
    }

    private static void requireFields(String[] fields, int expected) {
        if (fields.length != expected) {
            throw new IllegalArgumentException("Expected " + expected
                    + " fields but found " + fields.length);
        }
    }
}