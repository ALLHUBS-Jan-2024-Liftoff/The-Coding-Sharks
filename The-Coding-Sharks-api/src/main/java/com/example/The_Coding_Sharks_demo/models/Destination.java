package com.example.The_Coding_Sharks_demo.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Destination extends AbstractEntity {
    
    @ManyToMany(mappedBy="destinationList")
    private List<Trip> trips = new ArrayList<>();

    private Number latitude;
    private Number longitude;

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
