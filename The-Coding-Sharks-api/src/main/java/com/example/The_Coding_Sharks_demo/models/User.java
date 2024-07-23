package com.example.The_Coding_Sharks_demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;



@Entity
public class User extends AbstractEntity{


    @NotBlank
    private String password;

    private String email;

    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;

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
    
    // Use the inherited "Name" field as the username

    public void setUsername(String username) {
        this.setName(username);
    }

    public String getUsername() {
        return this.getName();
    }

    public User() {

    }


    
    
}
