package com.example.The_Coding_Sharks_demo.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Trip extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "primary_user_id")
    private User primaryUser;

    @ManyToMany(mappedBy = "trips")
    private List<User> secondaryUsers = new ArrayList<>();

    @ManyToMany(mappedBy = "trips")
    private List<Destination> destinationList = new ArrayList<>();

    public User getPrimaryUser() {
        return primaryUser;
    }

    public void setPrimaryUser(User primaryUser) {
        this.primaryUser = primaryUser;
    }

    public List<User> getSecondaryUsers() {
        return secondaryUsers;
    }

    public void setSecondaryUsers(List<User> secondaryUsers) {
        this.secondaryUsers = secondaryUsers;
    }

    public List<Destination> getDestinationList() {
        return destinationList;
    }

    public void setDestinations(List<Destination> destinationList) {
        this.destinationList = destinationList;
    }
    


     
}
