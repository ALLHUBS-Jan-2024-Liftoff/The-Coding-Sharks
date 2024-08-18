package com.example.The_Coding_Sharks_demo.models.data;

import com.example.The_Coding_Sharks_demo.models.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TripRepository extends CrudRepository<Trip, Integer> {
    List<Trip> findByPrimaryUser_Id(Integer primaryUserId);
    List<Trip> findByName(String name);
//    List<Trip> findByUser(User user)

}
