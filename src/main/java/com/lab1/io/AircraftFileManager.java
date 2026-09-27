package com.lab1.io;

import com.lab1.model.Aircraft;
import com.lab1.model.AircraftFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AircraftFileManager {
    public List<Aircraft> loadFleet(String filename) throws IOException {
        List<Aircraft> fleet = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] fields = line.split(";");
                String type = fields[0];
                String[] data = new String[fields.length - 1];
                System.arraycopy(fields, 1, data, 0, fields.length - 1);

                Aircraft aircraft = AircraftFactory.createAircraft(type, data);
                fleet.add(aircraft);
            }
        }

        return fleet;
    }
}