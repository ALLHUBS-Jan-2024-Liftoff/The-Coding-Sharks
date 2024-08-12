package com.example.The_Coding_Sharks_demo.controllers;

import com.example.The_Coding_Sharks_demo.models.Destination;
import com.example.The_Coding_Sharks_demo.services.DestinationService;
import com.example.The_Coding_Sharks_demo.services.GeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private GeocodingService geocodingService;

    @PostMapping
    public ResponseEntity<Destination> createDestination(@RequestParam String name) {

        try {
            // Geocode the destination name to get latitude and longitude
            Destination geocodedDestination = geocodingService.geocodeDestination(name);

            // Save the geocoded destination to the database
            Destination createdDestination = destinationService.saveDestination(geocodedDestination);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdDestination); // 201 response
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 response
        }
    }

    @GetMapping
    public ResponseEntity<List<Destination>> getAllDestinations() {
        List<Destination> destinations = destinationService.getAllDestinations();
        System.out.println("TESTING");
        return ResponseEntity.ok(destinations); //200 response

    }

    @GetMapping("/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable Integer id) {
        Optional<Destination> destination = destinationService.getDestinationById(id);
        return destination.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); //404 response. must use .build() when no body is included
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestination(@PathVariable Integer id) {
        if (!destinationService.existsById(id)) {
            return ResponseEntity.notFound().build(); // 204 response
        }
        destinationService.deleteDestination(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/trips/{tripId}")
    public ResponseEntity<List<Destination>> getDestinationsForTrip(@PathVariable Integer tripId) {
        List<Destination> destinations = destinationService.getDestinationsForTrip(tripId);
        return ResponseEntity.ok(destinations);
    }
}

