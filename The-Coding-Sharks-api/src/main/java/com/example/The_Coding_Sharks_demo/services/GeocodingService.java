package com.example.The_Coding_Sharks_demo.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.example.The_Coding_Sharks_demo.models.Destination;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Service
public class GeocodingService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public GeocodingService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Destination geocodeDestination(String destinationName) throws Exception {
        String encodedDestinationName = URLEncoder.encode(destinationName, StandardCharsets.UTF_8.toString());

        String apiKey = "3843f958ea2f4ba0beca427b6f821857";
        String url = "https://api.geoapify.com/v1/geocode/search?text=" + encodedDestinationName + "&apiKey=" + apiKey;


        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return parseLatLonFromJson(response.body(), destinationName);
        } else {
            throw new RuntimeException("Failed to fetch geocoding data: " + response.statusCode());
        }
    }

    private Destination parseLatLonFromJson(String json, String destinationName) throws IOException {
        // Parse JSON response using ObjectMapper
        JsonNode rootNode = objectMapper.readTree(json);

        JsonNode featuresNode = rootNode.path("features");

        if (featuresNode.isArray() && featuresNode.size() > 0) {
            JsonNode firstFeature = featuresNode.get(0);
            JsonNode geometryNode = firstFeature.path("geometry");
            JsonNode coordinatesNode = geometryNode.path("coordinates");

            if (coordinatesNode.isArray() && coordinatesNode.size() >= 2) {
                Double longitude = coordinatesNode.get(0).asDouble();
                Double latitude = coordinatesNode.get(1).asDouble();

                return new Destination(destinationName, latitude, longitude);
            } else {
                throw new RuntimeException("No coordinates found in geocoding results");
            }
        } else {
            throw new RuntimeException("No features found in geocoding results");
        }
    }

}



