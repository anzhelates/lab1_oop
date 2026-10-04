package com.lab1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CargoAircraftTest {

    private CargoAircraft aircraft;

    @BeforeEach
    void setUp() {
        aircraft = new CargoAircraft("N601CG", "Airbus", "A300-600F",
                7500, 5500, 48000);
    }

    @Test
    void carriesCargoButNoPassengers() {
        assertEquals("N601CG", aircraft.getRegistration());
        assertEquals("Airbus A300-600F", aircraft.getDisplayName());
        assertEquals(0, aircraft.getPassengerCapacity());
        assertEquals(48000, aircraft.getCargoCapacityKg());
    }

    @Test
    void rejectsNonPositiveCargoCapacity() {
        assertThrows(IllegalArgumentException.class,
                () -> new CargoAircraft("N602CG", "Airbus", "A300-600F",
                        7500, 5500, 0));
        assertThrows(IllegalArgumentException.class,
                () -> new CargoAircraft("N602CG", "Airbus", "A300-600F",
                        7500, 5500, -1));
    }
}