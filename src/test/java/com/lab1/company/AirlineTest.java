package com.lab1.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.lab1.model.Aircraft;
import com.lab1.model.CargoAircraft;
import com.lab1.model.PassengerAircraft;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AirlineTest {

    private Airline airline;

    @BeforeEach
    void setUp() {
        airline = new Airline("SkyLine Airways");
    }

    private Aircraft mockAircraft(String registration, double rangeKm,
                                  double fuelPerHour) {
        Aircraft aircraft = mock(Aircraft.class);
        when(aircraft.getRegistration()).thenReturn(registration);
        when(aircraft.getMaxRangeKm()).thenReturn(rangeKm);
        when(aircraft.getFuelPerHour()).thenReturn(fuelPerHour);
        when(aircraft.canFly(1000)).thenReturn(rangeKm >= 1000);
        return aircraft;
    }

    @Test
    void validatesAndKeepsName() {
        assertEquals("SkyLine Airways", airline.getName());
        assertThrows(IllegalArgumentException.class,
                () -> new Airline(" "));
        assertThrows(IllegalArgumentException.class,
                () -> new Airline(null));
    }

    @Test
    void addsAndRemovesAircraft() {
        Aircraft aircraft = mockAircraft("N1", 1000, 1000);

        airline.addAircraft(aircraft);
        assertEquals(List.of(aircraft), airline.getFleet());

        assertTrue(airline.removeAircraft(aircraft));
        assertFalse(airline.removeAircraft(aircraft));
        assertTrue(airline.getFleet().isEmpty());
    }

    @Test
    void rejectsNullAndDuplicateRegistration() {
        airline.addAircraft(mockAircraft("N1", 1000, 1000));

        assertThrows(IllegalArgumentException.class,
                () -> airline.addAircraft(null));
        assertThrows(IllegalArgumentException.class,
                () -> airline.addAircraft(mockAircraft("N1", 2000, 2000)));
    }

    @Test
    void returnedFleetIsACopy() {
        airline.addAircraft(mockAircraft("N1", 1000, 1000));

        airline.getFleet().clear();

        assertEquals(1, airline.getFleet().size());
    }

    @Test
    void sumsPassengerAndCargoCapacity() {
        assertEquals(0, airline.calculateTotalPassengerCapacity());
        assertEquals(0.0, airline.calculateTotalCargoCapacityKg(), 1e-9);

        airline.addAircraft(new PassengerAircraft("N1", "Embraer", "E175",
                3700, 2500, 76));
        airline.addAircraft(new CargoAircraft("N2", "Boeing", "767-300F",
                6025, 5700, 58000));

        assertEquals(76, airline.calculateTotalPassengerCapacity());
        assertEquals(58000.0, airline.calculateTotalCargoCapacityKg(), 1e-9);
    }

    @Test
    void sortsByRangeWithoutChangingTheFleet() {
        Aircraft far = mockAircraft("N1", 9000, 1000);
        Aircraft near = mockAircraft("N2", 1500, 1000);
        Aircraft middle = mockAircraft("N3", 4000, 1000);
        airline.addAircraft(far);
        airline.addAircraft(near);
        airline.addAircraft(middle);

        assertEquals(List.of(near, middle, far),
                airline.getFleetSortedByRange());
        assertEquals(List.of(far, near, middle), airline.getFleet());
    }

    @Test
    void findsByFuelConsumptionRangeInclusive() {
        Aircraft low = mockAircraft("N1", 1000, 600);
        Aircraft lowerBound = mockAircraft("N2", 1000, 5000);
        Aircraft upperBound = mockAircraft("N3", 1000, 7000);
        Aircraft high = mockAircraft("N4", 1000, 9500);
        airline.addAircraft(low);
        airline.addAircraft(lowerBound);
        airline.addAircraft(upperBound);
        airline.addAircraft(high);

        assertEquals(List.of(lowerBound, upperBound),
                airline.findByFuelConsumptionRange(5000, 7000));
        assertTrue(airline.findByFuelConsumptionRange(10000, 11000)
                .isEmpty());
    }

    @Test
    void rejectsInvalidFuelRange() {
        assertThrows(IllegalArgumentException.class,
                () -> airline.findByFuelConsumptionRange(-1, 10));
        assertThrows(IllegalArgumentException.class,
                () -> airline.findByFuelConsumptionRange(10, 5));
    }

    @Test
    void findsAircraftForRoute() {
        Aircraft able = mockAircraft("N1", 1500, 1000);
        Aircraft unable = mockAircraft("N2", 500, 1000);
        airline.addAircraft(able);
        airline.addAircraft(unable);

        assertEquals(List.of(able), airline.findAircraftForRoute(1000));
        assertThrows(IllegalArgumentException.class,
                () -> airline.findAircraftForRoute(0));
    }
}