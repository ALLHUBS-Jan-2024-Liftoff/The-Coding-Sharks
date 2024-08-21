package com.example.The_Coding_Sharks_demo;

import com.example.The_Coding_Sharks_demo.controllers.AuthenticationController;
import com.example.The_Coding_Sharks_demo.models.User;
import com.example.The_Coding_Sharks_demo.models.data.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//
//public class AuthenticationFilter implements HandlerInterceptor {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    AuthenticationController authenticationController;
//
//    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css");
//
//    private static boolean isWhitelisted(String path) {
//        for (String pathRoot : whitelist) {
//            if (path.startsWith(pathRoot)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response,
//                             Object handler) throws IOException {
//
//        // Don't require sign-in for whitelisted pages
//        if (isWhitelisted(request.getRequestURI())) {
//            // returning true indicates that the request may proceed
//            return true;
//        }
//
//        HttpSession session = request.getSession();
//        User user = authenticationController.getUserFromSession(session);
//
//        // The user is logged in
//        if (user != null) {
//            return true;
//        }
//
//        // The user is NOT logged in
//        response.sendRedirect("/login");
//        return false;
//    }
//
//}



//public class AuthenticationFilter implements HandlerInterceptor {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    AuthenticationController authenticationController;
//
//    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css", "/api/.*");
//
//    private static boolean isWhitelisted(String path) {
//        for (String pathRoot : whitelist) {
//            if (path.matches(pathRoot)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response,
//                             Object handler) throws IOException {
//
//        String requestURI = request.getRequestURI();
//        System.out.println("Request URI: " + requestURI);
//
//        // Don't require sign-in for whitelisted pages
//        if (isWhitelisted(requestURI)) {
//            System.out.println("Whitelisted path: " + requestURI);
//            return true;
//        }
//
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            User user = authenticationController.getUserFromSession(session);
//            if (user != null) {
//                return true;
//            }
//        }
//
//        // Log redirection for debugging
//        System.out.println("Redirecting to login. Request URI: " + requestURI);
//        System.out.println("Requesting: " + request.getRequestURI());
//
//        response.sendRedirect("/login");
//        return false;
//    }
//}