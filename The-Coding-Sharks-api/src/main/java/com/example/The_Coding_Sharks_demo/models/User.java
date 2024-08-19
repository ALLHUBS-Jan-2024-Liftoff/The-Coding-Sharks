package com.example.The_Coding_Sharks_demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
public class User extends AbstractEntity {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank  //changed from NotNull so that it will reject an empty string/only whitespace.
    private String username;

    @NotBlank //changed from NotNull also
    private String pwHash;

    private String firstName;
    private String lastName;

    @NotEmpty //allows non-empty strings but doesn't enforce whitespace constraints
    private String email;


    @ManyToMany//(mappedBy = "secondaryUsers") //added this to link to Trip entity (+ getters and setters)
    @JoinTable(
            name = "user_trip",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "trip_id")
    )
    private List<Trip> trips = new ArrayList<>();

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    //constructors
    public User() {}

    public User(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }



    // Use the inherited "Name" field as the username

    // Override setName to also set username
    @Override
    public void setName(String name) {
        super.setName(name);
        this.username = name; // so username is always set to the inherited name
    }

    // Override getName to return username
    @Override
    public String getName() {
        return this.username;
    }





    public void setPassword(String password) {
        // Hash the password before storing it
        this.pwHash = encoder.encode(password);
    }

    public boolean verifyPassword(String rawPassword) {
        // Verify the raw password against the hashed pwHash
        return encoder.matches(rawPassword, this.pwHash);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

}

