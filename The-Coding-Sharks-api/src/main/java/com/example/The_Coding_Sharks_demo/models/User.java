package com.example.The_Coding_Sharks_demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;


@Entity
public class User extends AbstractEntity{

    @NotBlank  //changed from NotNull so that it will reject an empty string/only whitespace.
    private String username;

    @NotBlank //changed from NotNull also
    private String pwHash;

    @NotBlank
    private String password;

    @NotEmpty //allows non-empty strings but doesn't enforce whitespace constraints
    private String email;

    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;

    @ManyToMany(mappedBy = "secondaryUsers") //added this to link to Trip entity (+ getters and setters)
    private Set<Trip> trips = new HashSet<>();

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

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }

    // Use the inherited "Name" field as the username

    public void setUsername(String username) {
        this.setName(username);
    }

    public String getUsername() {
        return username + this.getName();
    }

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public void setPassword(String password) {
        // Hash the password before storing it
        this.pwHash = new BCryptPasswordEncoder().encode(password);
    }

    public boolean verifyPassword(String rawPassword) {
        // Verify the raw password against the hashed pwHash
        return new BCryptPasswordEncoder().matches(rawPassword, this.pwHash);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

}

