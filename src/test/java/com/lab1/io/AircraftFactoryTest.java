package com.lab1.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.lab1.model.Aircraft;
import com.lab1.model.CargoAircraft;
import com.lab1.model.PassengerAircraft;

import org.junit.jupiter.api.Test;

class AircraftFactoryTest {

    private IllegalArgumentException createFails(String... fields) {
        return assertThrows(IllegalArgumentException.class,
                () -> AircraftFactory.createAircraft(fields));
    }

    @Test
    void createsPassengerAircraft() {
        Aircraft aircraft = AircraftFactory.createAircraft(new String[] {
                "passenger", "N1", "Embraer", "E175", "3700", "2500", "76",
                "1500"});

        PassengerAircraft passenger =
                assertInstanceOf(PassengerAircraft.class, aircraft);
        assertEquals("N1", passenger.getRegistration());
        assertEquals("Embraer E175", passenger.getDisplayName());
        assertEquals(76, passenger.getPassengerCapacity());
        assertEquals(1500, passenger.getCargoCapacityKg());
    }

    @Test
    void createsCargoAircraft() {
        Aircraft aircraft = AircraftFactory.createAircraft(new String[] {
                "CARGO", "N2", "Boeing", "747-8F", "9200", "8000", "134000"});

        CargoAircraft cargo = assertInstanceOf(CargoAircraft.class, aircraft);
        assertEquals("N2", cargo.getRegistration());
        assertEquals(134000, cargo.getCargoCapacityKg());
    }

    @Test
    void rejectsUnknownType() {
        IllegalArgumentException e = createFails("HELICOPTER", "N2");

        assertTrue(e.getMessage().contains("Unknown aircraft type"),
                e.getMessage());
    }

    @Test
    void rejectsMissingOrWrongNumberOfFields() {
        createFails((String[]) null);
        createFails();
        createFails("PASSENGER", "N1", "Embraer", "E175", "3700", "2500",
                "76");
        createFails("CARGO", "N2", "Boeing", "747-8F", "9200", "8000");
    }

    @Test
    void rejectsBadOrInvalidNumbers() {
        createFails("PASSENGER", "N1", "Embraer", "E175", "3700", "2500",
                "many", "1500");
        createFails("CARGO", "N2", "Boeing", "747-8F", "-5", "8000",
                "134000");
    }
}