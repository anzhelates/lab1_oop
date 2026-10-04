package com.lab1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PassengerAircraftTest {

    private PassengerAircraft aircraft;

    @BeforeEach
    void setUp() {
        aircraft = new PassengerAircraft(" N201SL ", "Embraer", "E175",
                3700, 2500, 76, 1500);
    }

    @Test
    void exposesAircraftData() {
        assertEquals("N201SL", aircraft.getRegistration());
        assertEquals("Embraer", aircraft.getManufacturer());
        assertEquals("E175", aircraft.getModel());
        assertEquals("Embraer E175", aircraft.getDisplayName());
        assertEquals(3700, aircraft.getMaxRangeKm());
        assertEquals(2500, aircraft.getFuelPerHour());
        assertEquals(76, aircraft.getPassengerCapacity());
        assertEquals(1500, aircraft.getCargoCapacityKg());
        assertEquals("N201SL (Embraer E175)", aircraft.toString());
    }

    @Test
    void canFlyIsLimitedByRange() {
        assertTrue(aircraft.canFly(3700));
        assertFalse(aircraft.canFly(3701));
        assertFalse(aircraft.canFly(0));
    }

    @Test
    void allowsZeroCargoHold() {
        assertEquals(0, create("N1", "Embraer", 3700, 2500, 76, 0)
                .getCargoCapacityKg());
    }

    @Test
    void rejectsInvalidArguments() {
        assertThrows(IllegalArgumentException.class,
                () -> create("", "Embraer", 3700, 2500, 76, 1500));
        assertThrows(IllegalArgumentException.class,
                () -> create("N1", "Embraer", 0, 2500, 76, 1500));
        assertThrows(IllegalArgumentException.class,
                () -> create("N1", "Embraer", 3700, 2500, 0, 1500));
    }

    private PassengerAircraft create(String registration,
                                     String manufacturer, double range,
                                     double fuel, int seats, double hold) {
        return new PassengerAircraft(registration, manufacturer, "E175",
                range, fuel, seats, hold);
    }
}