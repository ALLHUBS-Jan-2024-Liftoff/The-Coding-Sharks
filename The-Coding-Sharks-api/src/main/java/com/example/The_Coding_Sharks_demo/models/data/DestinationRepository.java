package com.example.The_Coding_Sharks_demo.models.data;

import com.example.The_Coding_Sharks_demo.models.Destination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends CrudRepository<Destination, Integer> {
    
}
