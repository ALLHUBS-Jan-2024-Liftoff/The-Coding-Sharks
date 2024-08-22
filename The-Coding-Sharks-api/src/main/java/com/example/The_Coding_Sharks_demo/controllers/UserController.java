package com.example.The_Coding_Sharks_demo.controllers;

import com.example.The_Coding_Sharks_demo.models.DTO.LoginFormDTO;
import com.example.The_Coding_Sharks_demo.models.DTO.RegisterFormDTO;
import com.example.The_Coding_Sharks_demo.models.User;
import com.example.The_Coding_Sharks_demo.models.data.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    @GetMapping("/currentUser")
    public ResponseEntity<User> getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentUser = getUserFromSession(session);

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping  //returns a list of all user objects
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/{id}") //returns the user if found (using ID), and 404 not found if not
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
        User newUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (Exception e) {
        e.printStackTrace(); // Log the exception
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();

        // Apply updates based on the provided fields
        updates.forEach((key, value) -> {
            switch (key) {
                case "email":
                    user.setEmail((String) value);
                    break;
                case "firstName":
                    user.setFirstName((String) value);
                    break;
                case "lastName":
                    user.setLastName((String) value);
                    break;
                // Add other fields as needed
            }
        });

        return ResponseEntity.ok(userRepository.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value= "/register" )
    public ResponseEntity<Map> processRegistrationForm(@RequestBody RegisterFormDTO registerFormDTO,
                                                       HttpServletRequest request)  {
        ResponseEntity response = null;
        Map<String, String> responseBody = new HashMap<>();
        try{
            User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());
            if (existingUser == null && !registerFormDTO.getUsername().isEmpty() && !registerFormDTO.getPassword().isEmpty()){
                responseBody.put("message", "Given user details are successfully registered");
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(responseBody);
                User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword(), registerFormDTO.getFirstName(), registerFormDTO.getLastName(), registerFormDTO.getEmail());
                setUserInSession(request.getSession(), newUser);
                userRepository.save(newUser);
            } else if(existingUser != null) {
                responseBody.put("message", "User Already Exists.");
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(responseBody);
            } else if(registerFormDTO.getUsername().isEmpty()) {
                responseBody.put("message", "Username required.");
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(responseBody);
            } else if (registerFormDTO.getPassword().isEmpty()) {
                responseBody.put("message", "Password required");
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(responseBody);
            }
        }catch (Exception ex){
            responseBody.put("message", "An exception occurred due to " + ex.getMessage());
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseBody);
        }
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<Map> processLoginForm(@RequestBody LoginFormDTO loginFormDTO, HttpServletRequest request) {

        ResponseEntity response = null;
        Map<String, String> responseBody = new HashMap<>();
        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());
        String password = loginFormDTO.getPassword();
        if (theUser == null) {
            responseBody.put("message", "Username does not exist");
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(responseBody);
        }else if (!theUser.isMatchingPassword(password)) {
            responseBody.put("message", "Password does not match");
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(responseBody);
        } else {
            setUserInSession(request.getSession(), theUser);
            responseBody.put("message", "User successfully logged in.");
            responseBody.put("username", theUser.getUsername());
            response = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(responseBody);
        }
        return  response;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }


}
