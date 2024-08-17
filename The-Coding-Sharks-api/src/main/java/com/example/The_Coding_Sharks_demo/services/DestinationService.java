package com.example.The_Coding_Sharks_demo.services;

import com.example.The_Coding_Sharks_demo.models.Destination;
import com.example.The_Coding_Sharks_demo.models.Trip;
import com.example.The_Coding_Sharks_demo.models.data.DestinationRepository;
import com.example.The_Coding_Sharks_demo.models.data.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private TripRepository tripRepository;

    // Find or create a destination by name
    public Destination findOrCreateDestination(String destinationName) {
        Destination existingDestination = destinationRepository.findByName(destinationName);
        if (existingDestination == null) {
            // Create new destination if it does not exist
            Destination newDestination = new Destination();
            newDestination.setName(destinationName);
            existingDestination = destinationRepository.save(newDestination);
        }
        return existingDestination;
    }


    // Get all destinations
    public List<Destination> getAllDestinations() {
        return (List<Destination>) destinationRepository.findAll();
    }

    // Get destination by ID
    public Optional<Destination> getDestinationById(Integer id) {
        return destinationRepository.findById(id);
    }

    // Save
    public Destination saveDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    // Delete a destination by ID
    public void deleteDestination(Integer id) {
        destinationRepository.deleteById(id);
    }

    // Check if destination exists by ID
    public boolean existsById(Integer id) {
        return destinationRepository.existsById(id);
    }

    // Get destinations for a specific trip
    public List<Destination> getDestinationsForTrip(Integer tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
        return trip.getDestinationList();
    }
}

