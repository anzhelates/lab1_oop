package com.lab1.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.lab1.model.Aircraft;
import com.lab1.model.CargoAircraft;
import com.lab1.model.PassengerAircraft;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import org.junit.jupiter.api.Test;

class AircraftLoaderTest {

    private final AircraftLoader loader = new AircraftLoader();

    @Test
    void loadsAircraftAndIgnoresBlankLinesAndSpaces() throws IOException {
        String text = "\n   \n"
                + "PASSENGER;N1;Embraer;E175;3700;2500;76\n"
                + " CARGO ; N2 ; Boeing ; 747-8F ; 9200 ; 8000 ; 134000 \n";

        List<Aircraft> fleet = loader.load(new StringReader(text));

        assertEquals(2, fleet.size());
        assertInstanceOf(PassengerAircraft.class, fleet.get(0));
        assertInstanceOf(CargoAircraft.class, fleet.get(1));
        assertEquals("N2", fleet.get(1).getRegistration());
    }

    @Test
    void reportsLineNumberOfInvalidLine() {
        String text = "CARGO;N2;Boeing;747-8F;9200;8000;134000\n"
                + "\nCARGO;N3;Boeing\n";

        IOException e = assertThrows(IOException.class,
                () -> loader.load(new StringReader(text)));

        assertTrue(e.getMessage().startsWith("Line 3:"), e.getMessage());
    }

    @Test
    void loadsFleetFromClasspath() throws IOException {
        assertEquals(15,
                loader.loadFromResource("/aircraft_data.txt").size());
    }

    @Test
    void reportsMissingResource() {
        IOException e = assertThrows(IOException.class,
                () -> loader.loadFromResource("/missing.txt"));

        assertTrue(e.getMessage().contains("/missing.txt"), e.getMessage());
    }
}