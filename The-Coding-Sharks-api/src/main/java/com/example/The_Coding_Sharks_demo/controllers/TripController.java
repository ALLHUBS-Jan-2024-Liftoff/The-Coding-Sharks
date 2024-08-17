package com.example.The_Coding_Sharks_demo.controllers;

import com.example.The_Coding_Sharks_demo.models.Trip;
import com.example.The_Coding_Sharks_demo.services.DestinationService;
import com.example.The_Coding_Sharks_demo.services.GeocodingService;
import com.example.The_Coding_Sharks_demo.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private GeocodingService geocodingService;

    // Create a trip using a name the user creates
    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestParam String name) {
        try {
            Trip createdTrip = tripService.createTrip(name);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTrip); // 201 response
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 response
        }
    }

    // Get all trips
    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        try {
            List<Trip> trips = tripService.getAllTrips();
            if (trips.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 response
            }
            return ResponseEntity.ok(trips); // 200 response
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 response
        }
    }

    // Get all trips for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Trip>> getAllTripsByUser(@PathVariable Integer userId) {
        try {
            List<Trip> trips = tripService.getTripsByUserId(userId);
            if (trips.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 response
            }
            return ResponseEntity.ok(trips); // 200 response
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 response
        }
    }

    // Get a specific trip
    @GetMapping("/{tripId}")
    public ResponseEntity<Trip> getTripById(@PathVariable Integer tripId) {
        try {
            Trip trip = tripService.getTripById(tripId);
            if (trip != null) {
                return ResponseEntity.ok(trip); // 200 response with the trip
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 response
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 response
        }
    }

    // Edit a specific trip
    @PutMapping("/{tripId}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Integer tripId, @RequestParam String name) {
        try {
            Trip updatedTrip = tripService.updateTrip(tripId, name);
            if (updatedTrip != null) {
                return ResponseEntity.ok(updatedTrip); // 200 response with the updated trip
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 response
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 response
        }
    }

    // Delete a specific trip
    @DeleteMapping("/{tripId}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Integer tripId) {
        try {
            boolean isDeleted = tripService.deleteTrip(tripId);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 response
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 response
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 response
        }
    }
}
