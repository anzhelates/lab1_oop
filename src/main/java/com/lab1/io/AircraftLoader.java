package com.lab1.io;

import com.lab1.model.Aircraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AircraftLoader {

    private static final String FIELD_SEPARATOR_REGEX = "\\s*;\\s*";

    public List<Aircraft> loadFromResource(String name) throws IOException {
        try (InputStream stream = AircraftLoader.class.getResourceAsStream(name)) {
            if (stream == null) {
                throw new IOException("Resource not found: " + name);
            }
            Reader reader =
                    new InputStreamReader(stream, StandardCharsets.UTF_8);
            return load(reader);
        }
    }

    public List<Aircraft> load(Reader reader) throws IOException {
        List<Aircraft> aircraft = new ArrayList<>();
        BufferedReader buffered = new BufferedReader(reader);
        int lineNumber = 0;
        String line;
        while ((line = buffered.readLine()) != null) {
            lineNumber++;
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            try {
                aircraft.add(AircraftFactory.createAircraft(
                        line.split(FIELD_SEPARATOR_REGEX, -1)));
            } catch (IllegalArgumentException e) {
                throw new IOException(
                        "Line " + lineNumber + ": " + e.getMessage(), e);
            }
        }
        return aircraft;
    }
}