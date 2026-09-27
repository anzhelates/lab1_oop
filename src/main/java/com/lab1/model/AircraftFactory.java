package com.lab1.model;

public class AircraftFactory {

    public static Aircraft createAircraft(String type, String[] parts) {
        try {
            return switch (type) {
                case "REGIONAL" -> new RegionalJet(parts[0], parts[1],
                        Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]),
                        Integer.parseInt(parts[5]), Double.parseDouble(parts[6]), Double.parseDouble(parts[7]));
                case "WIDEBODY" -> new WideBodyJet(parts[0], parts[1],
                        Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]),
                        Integer.parseInt(parts[5]), Double.parseDouble(parts[6]), Boolean.parseBoolean(parts[7]));
                case "CARGO" -> new CargoAircraft(parts[0], parts[1],
                        Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]),
                        Integer.parseInt(parts[5]), Double.parseDouble(parts[6]));
                default -> null;
            };
        } catch (Exception e) {
            System.err.println("Error parsing line: " + type + " " + String.join("|", parts));
            return null;
        }
    }
}