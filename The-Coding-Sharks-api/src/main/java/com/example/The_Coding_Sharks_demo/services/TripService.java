package com.example.The_Coding_Sharks_demo.services;
import com.example.The_Coding_Sharks_demo.models.Destination;
import com.example.The_Coding_Sharks_demo.models.Trip;
import com.example.The_Coding_Sharks_demo.models.User;
import com.example.The_Coding_Sharks_demo.models.data.TripRepository;
import com.example.The_Coding_Sharks_demo.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // Create trip using Name
    public Trip createTrip(String tripName) {
            Trip newTrip = new Trip();
            newTrip.setName(tripName);
        return tripRepository.save(newTrip);
    }

    //Get all trips in the database
    public List<Trip> getAllTrips(){
        return (List<Trip>) tripRepository.findAll();
    }

    // Get all trips for a user
    public List<Trip> getTripsByUserId(Integer userId) {
        return tripRepository.findByPrimaryUser_Id(userId);
    }

    // Get a specific trip by id
    public Trip getTripById(Integer tripId) {
        return tripRepository.findById(tripId).orElse(null);
    }

    // Update a trip
    public Trip updateTrip(Integer tripId, String name) {
        Optional<Trip> tripOptional = tripRepository.findById(tripId);
        if (tripOptional.isPresent()) {
            Trip tripToUpdate = tripOptional.get();
            tripToUpdate.setName(name);
            return tripRepository.save(tripToUpdate);
        } else {
            return null;
        }
    }

    // Delete a trip
    public boolean deleteTrip(Integer tripId) {
        Optional<Trip> tripOptional = tripRepository.findById(tripId);
        if (tripOptional.isPresent()) {
            tripRepository.deleteById(tripId);
            return true;
        } else {
            return false;
        }
    }
    public void addDestinationToTrip(int tripId, String destinationName) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new IllegalArgumentException("Trip not found"));
        Destination destination = destinationService.findOrCreateDestination(destinationName);

        // Check if the destination is already in the trip's list of destinations
        if (!trip.getDestinationList().contains(destination)) {
            trip.getDestinationList().add(destination);
            tripRepository.save(trip);
        }
    }

    // Add a secondary user to a trip
    public void addUserToTrip(int tripId, String personUsername) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new IllegalArgumentException("Trip not found"));
        User newUser = userService.findByUsername(personUsername);

        // Check if user is already in trip's secondary user list
        if (!trip.getSecondaryUsers().contains(newUser)) {
            trip.getSecondaryUsers().add(newUser);
            tripRepository.save(trip);
        }

    }

    // Remove a secondary user from a trip
    public void removeUserFromTrip(int tripId, String personUsername) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new IllegalArgumentException("Trip not found"));
        User newUser = userService.findByUsername(personUsername);
        
        if (trip.getSecondaryUsers().contains(newUser)) {
            trip.getSecondaryUsers().remove(newUser);
            tripRepository.save(trip);
        }   

    }

}

