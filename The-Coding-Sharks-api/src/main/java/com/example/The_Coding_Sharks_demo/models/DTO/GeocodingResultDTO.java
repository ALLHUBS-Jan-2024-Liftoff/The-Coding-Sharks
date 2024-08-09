package com.example.The_Coding_Sharks_demo.models.DTO;
import java.util.List;

public class GeocodingResultDTO {
    private List<Result> results;

    // Getters and setters
    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public static class Result {
        private int lat;
        private int lon;
        private String formatted;

        // Getters and setters
        public double getLat() {
            return lat;
        }

        public void setLat(int lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(int lon) {
            this.lon = lon;
        }

        public String getFormatted() {
            return formatted;
        }

        public void setFormatted(String formatted) {
            this.formatted = formatted;
        }
    }
}
