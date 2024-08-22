package com.example.The_Coding_Sharks_demo.controllers;

import com.example.The_Coding_Sharks_demo.models.Destination;
import com.example.The_Coding_Sharks_demo.services.DestinationService;
import com.example.The_Coding_Sharks_demo.services.GeocodingService;
import com.example.The_Coding_Sharks_demo.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
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

    @Autowired
    private TripService tripService;

    // Create a destination and add it to a trip
    @PostMapping("/addDestination/{tripId}")
    public ResponseEntity<String> createDestinationAndAddToTrip(
            @PathVariable int tripId,
            @RequestParam String destinationName) {
        try {
            // Geocode the destination name to get latitude and longitude
            Destination geocodedDestination = geocodingService.geocodeDestination(destinationName);

            if (geocodedDestination == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destination not found.");
            }

            // Use findOrCreateDestination to handle checking and creating destination
            Destination destination = destinationService.findOrCreateDestination(destinationName);
            // Update the destination with geocoded data if it's newly created
            if (destination.getLatitude() == null || destination.getLongitude() == null) {
                destination.setLatitude(geocodedDestination.getLatitude());
                destination.setLongitude(geocodedDestination.getLongitude());
                destinationService.saveDestination(destination);
            }

            // Add the destination to the trip
            tripService.addDestinationToTrip(tripId, destination.getName());
            return ResponseEntity.ok("Destination created and added to trip successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding destination to trip.");
        }
    }

    // Add a random destination to a trip
    @PostMapping("/addRandomDestination/{tripId}")
    public ResponseEntity<String> addRandomDestinationToTrip(
            @PathVariable int tripId,
            @RequestParam String selectedCity) {
        try {
            // Geocode the selected city to get latitude and longitude
            Destination geocodedDestination = geocodingService.geocodeDestination(selectedCity);

            if (geocodedDestination == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destination not found.");
            }

            // Use findOrCreateDestination to handle checking and creating destination
            Destination destination = destinationService.findOrCreateDestination(selectedCity);
            // Update the destination with geocoded data if it's newly created
            if (destination.getLatitude() == null || destination.getLongitude() == null) {
                destination.setLatitude(geocodedDestination.getLatitude());
                destination.setLongitude(geocodedDestination.getLongitude());
                destinationService.saveDestination(destination);
            }

            // Add the destination to the trip
            tripService.addDestinationToTrip(tripId, destination.getName());
            return ResponseEntity.ok("Random destination added to trip successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding destination to trip.");
        }
    }


    //Get all destinations in database for search feature
    @GetMapping
    public ResponseEntity<List<Destination>> getAllDestinations() {
        List<Destination> destinations = destinationService.getAllDestinations();
        return ResponseEntity.ok(destinations); //200 response

    }

    //Get all the destinations for a specific trip
    @GetMapping("/trips/{tripId}")
    public ResponseEntity<List<Destination>> getDestinationsForTrip(@PathVariable Integer tripId) {
        List<Destination> destinations = destinationService.getDestinationsForTrip(tripId);
        return ResponseEntity.ok(destinations);
    }

    //Get a specific destination
    @GetMapping("/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable Integer id) {

        Optional<Destination> destination = destinationService.getDestinationById(id);
        return destination.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); //404 response. must use .build() when no body is included
    }

    //Simply geocode the destination (used in randomDestination in UI)
    @GetMapping("/geocode")
    public ResponseEntity<?> getCityCoordinates(@RequestParam String text) {
        try {
            Destination destination = geocodingService.geocodeDestination(text);
            if (destination != null) {
                return ResponseEntity.ok(destination); // Return the geocoded destination
            } else {
                return ResponseEntity.notFound().build(); // City not found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 response
        }
    }

    //Delete a specific destination
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDestination(@PathVariable("id") Integer id ) {
        System.out.println("REACHED THIS");
        if (!destinationService.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404 response
        }
        destinationService.deleteDestination(id);
        return ResponseEntity.noContent().build(); //204 when deleted
    }

}

