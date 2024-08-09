package com.example.The_Coding_Sharks_demo.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.example.The_Coding_Sharks_demo.models.DTO.GeocodingResultDTO;
import com.example.The_Coding_Sharks_demo.models.Destination;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeocodingService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public GeocodingService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Destination geocodeDestination(String destinationName) throws Exception {
        String apiKey = "3843f958ea2f4ba0beca427b6f821857";
        String url = "https://api.geoapify.com/v1/geocode/search?text=" + destinationName + "&apiKey=" + apiKey;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            // Use streaming parser to extract lat and lon
            return parseLatLonFromJson(response.body(), destinationName);
        } else {
            throw new RuntimeException("Failed to fetch geocoding data: " + response.statusCode());
        }
    }

    private Destination parseLatLonFromJson(String json, String destinationName) throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonParser parser = factory.createParser(json)) {
            Double latitude = null;
            Double longitude = null;

            while (!parser.isClosed()) {
                JsonToken token = parser.nextToken();

                if (JsonToken.FIELD_NAME.equals(token) && "lat".equals(parser.getCurrentName())) {
                    parser.nextToken();
                    latitude = parser.getDoubleValue();
                }

                if (JsonToken.FIELD_NAME.equals(token) && "lon".equals(parser.getCurrentName())) {
                    parser.nextToken();
                    longitude = parser.getDoubleValue();
                }

                // Stop parsing once we have both latitude and longitude
                if (latitude != null && longitude != null) {
                    break;
                }
            }

            if (latitude != null && longitude != null) {
                return new Destination(destinationName, latitude, longitude);
            } else {
                throw new RuntimeException("No geocoding results found");
            }
        }
    }
}


