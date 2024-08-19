// package com.example.The_Coding_Sharks_demo;

// import com.example.The_Coding_Sharks_demo.controllers.AuthenticationController;
// import com.example.The_Coding_Sharks_demo.models.User;
// import com.example.The_Coding_Sharks_demo.models.data.UserRepository;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.http.HttpSession;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.servlet.HandlerInterceptor;

// import java.io.IOException;
// import java.util.Arrays;
// import java.util.List;

// public class AuthenticationFilter implements HandlerInterceptor {

//     @Autowired
//     UserRepository userRepository;

//     @Autowired
//     AuthenticationController authenticationController;

//     private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css");
// //add any pages users are allowed to hit without being authenticated, else have to be authenticated to view

//     private static boolean isWhitelisted(String path) {
//         for (String pathRoot : whitelist) {
//             if (path.startsWith(pathRoot)) {
//                 return true;
//             }
//         }
//         return false;
//     }

//     @Override
//     public boolean preHandle(HttpServletRequest request,
//                              HttpServletResponse response,
//                              Object handler) throws IOException {

//         // Don't require sign-in for whitelisted pages
//         if (isWhitelisted(request.getRequestURI())) {
//             // returning true indicates that the request may proceed
//             return true;
//         }

//         HttpSession session = request.getSession();
//         User user = authenticationController.getUserFromSession(session);

//         // The user is logged in
//         if (user != null) {
//             return true;
//         }

//         // The user is NOT logged in
//         if (request.getMethod().equals("OPTIONS")) {
//             //for preflight requests, respond with the necessary CORS headers
//             response.setStatus(HttpServletResponse.SC_OK);
//             return true;
//         } else {
//             //for other requests, respond with a CORS error
//             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             return false;
//         }
//     }
// }

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
//        response.sendRedirect("/login");
//        return false;
//    }
//}