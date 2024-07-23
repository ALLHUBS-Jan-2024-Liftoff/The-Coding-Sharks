package com.example.The_Coding_Sharks_demo.models.data;

import com.example.The_Coding_Sharks_demo.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    
}
