package com.example.The_Coding_Sharks_demo.models.data;

import com.example.The_Coding_Sharks_demo.models.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends CrudRepository<Trip, Integer>{
    
}
