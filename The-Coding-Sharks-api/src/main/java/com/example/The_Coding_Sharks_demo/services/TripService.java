package com.example.The_Coding_Sharks_demo.services;
import com.example.The_Coding_Sharks_demo.models.Destination;
import com.example.The_Coding_Sharks_demo.models.Trip;
import com.example.The_Coding_Sharks_demo.models.data.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private DestinationService destinationService;

    public void addDestinationToTrip(int tripId, String destinationName, Number latitude, Number longitude) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new IllegalArgumentException("Trip not found"));
        Destination destination = destinationService.findOrCreateDestination(destinationName, latitude, longitude);

        // Check if the destination is already in the trip's list of destinations
        if (!trip.getDestinationList().contains(destination)) {
            trip.getDestinationList().add(destination);
            tripRepository.save(trip);
        }
    }
}

