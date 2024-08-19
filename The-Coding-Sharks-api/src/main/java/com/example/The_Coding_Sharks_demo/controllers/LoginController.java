//package com.example.The_Coding_Sharks_demo.controllers;
//
//import com.example.The_Coding_Sharks_demo.models.DTO.LoginFormDTO;
//import com.example.The_Coding_Sharks_demo.models.User;
//import com.example.The_Coding_Sharks_demo.services.UserService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class LoginController {
//
//    @Autowired
//    private UserService userService;
//
//    private static final String userSessionKey = "user";
//
//    public static void setUserInSession(HttpSession session, User user) {
//        session.setAttribute(userSessionKey, user.getId());
//    }
//
//
//    @PostMapping("/login")
//    public ResponseEntity<?> processLoginForm(@RequestBody @Valid LoginFormDTO loginFormDTO,
//                                              Errors errors, HttpServletRequest request) {
//
//        if (errors.hasErrors()) {
//            return ResponseEntity.badRequest().body(errors.getAllErrors());
//        }
//
//        User theUser = userService.findByUsername(loginFormDTO.getUsername());
//
//        if (theUser == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }
//
//        String password = loginFormDTO.getPassword();
//
//        if (!theUser.isMatchingPassword(password)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }
//
//        setUserInSession(request.getSession(), theUser);
//
//        return ResponseEntity.ok("Login successful");
//    }
//
//    @GetMapping("/logout")
//    public ResponseEntity<?> logout(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//        return ResponseEntity.ok("Logout successful");
//    }
//}

