package com.example.The_Coding_Sharks_demo.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Destination extends AbstractEntity {

    @ManyToMany
    @JoinTable(
            name = "destination_trip",
            joinColumns = @JoinColumn(name = "destination_id"),
            inverseJoinColumns = @JoinColumn(name = "trip_id")
    )
    private List<Trip> trips = new ArrayList<>();

    private Number latitude;
    private Number longitude;

    public Destination(){};

    public Destination(String name, Number latitude, Number longitude) {
        this.setName(name); // getting name from AbstractEntity
        this.latitude = latitude;
        this.longitude = longitude;
    }


    //getters and setters
    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public Number getLatitude() {
        return latitude;
    }

    public void setLatitude(Number latitude) {
        this.latitude = latitude;
    }

    public Number getLongitude() {
        return longitude;
    }

    public void setLongitude(Number longitude) {
        this.longitude = longitude;
    }

}
