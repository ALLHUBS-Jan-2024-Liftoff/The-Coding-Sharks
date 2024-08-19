package com.example.The_Coding_Sharks_demo.controllers;

import com.example.The_Coding_Sharks_demo.models.DTO.LoginFormDTO;
import com.example.The_Coding_Sharks_demo.models.DTO.RegisterFormDTO;
import com.example.The_Coding_Sharks_demo.models.User;
import com.example.The_Coding_Sharks_demo.models.data.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@Controller
//public class AuthenticationController {
//
//    @Autowired
//    UserRepository userRepository;
//
//    private static final String userSessionKey = "user";
//
//    public User getUserFromSession(HttpSession session) {
//        Integer userId = (Integer) session.getAttribute(userSessionKey);
//        if (userId == null) {
//            return null;
//        }
//
//        Optional<User> user = userRepository.findById(userId);
//
//        if (user.isEmpty()) {
//            return null;
//        }
//
//        return user.get();
//    }
//
//    public static void setUserInSession(HttpSession session, User user) {
//        session.setAttribute(userSessionKey, user.getId());
//    }
//
//    @GetMapping("/register")
//    public String displayRegistrationForm(Model model) {
//        model.addAttribute(new RegisterFormDTO());
//        model.addAttribute("title", "Register");
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
//                                          Errors errors, HttpServletRequest request,
//                                          Model model) {
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Register");
//            return "register";
//        }
//
//        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());
//
//        if (existingUser != null) {
//            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
//            model.addAttribute("title", "Register");
//            return "register";
//        }
//
//        String password = registerFormDTO.getPassword();
//        String verifyPassword = registerFormDTO.getVerifyPassword();
//        if (!password.equals(verifyPassword)) {
//            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
//            model.addAttribute("title", "Register");
//            return "register";
//        }
//
//        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
//        userRepository.save(newUser);
//        setUserInSession(request.getSession(), newUser);
//
//        return "redirect:";
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request){
//        request.getSession().invalidate();
//        return "redirect:/login";
//    }

//}
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    private static final String userSessionKey = "user";

    // Method to retrieve user from session
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

    // Method to set user in session
    public static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    // Endpoint to handle user registration
    @PostMapping("/register")
    public ResponseEntity<?> processRegistrationForm(@RequestBody @Valid RegisterFormDTO registerFormDTO) {
        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser != null) {
            return new ResponseEntity<>("A user with that username already exists", HttpStatus.BAD_REQUEST);
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
        }

        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
        userRepository.save(newUser);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // Endpoint to handle user login
    @PostMapping("/login")
    public ResponseEntity<?> processLoginForm(@RequestBody @Valid LoginFormDTO loginFormDTO, HttpServletRequest request) {
        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if (theUser == null) {
            return new ResponseEntity<>("The given username does not exist", HttpStatus.BAD_REQUEST);
        }

        String password = loginFormDTO.getPassword();
        if (!theUser.isMatchingPassword(password)) {
            return new ResponseEntity<>("Invalid password", HttpStatus.BAD_REQUEST);
        }

        setUserInSession(request.getSession(), theUser);

        return new ResponseEntity<>(theUser, HttpStatus.OK);
    }

    // Endpoint to handle user logout
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

