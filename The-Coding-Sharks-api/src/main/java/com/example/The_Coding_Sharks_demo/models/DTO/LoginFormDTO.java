package com.example.The_Coding_Sharks_demo.models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static com.example.The_Coding_Sharks_demo.controllers.AuthenticationController.setUserInSession;

public class LoginFormDTO {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20, message = "Invalid username. Must be between 3 and 20 characters.")
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 30, message = "Invalid password. Must be between 5 and 30 characters.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //moved this to a LoginController instead
//    @GetMapping("/login")
//    public String displayLoginForm(Model model) {
//        model.addAttribute(new LoginFormDTO());
//        model.addAttribute("title", "Log In");
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
//                                   Errors errors, HttpServletRequest request,
//                                   Model model) {
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Log In");
//            return "login";
//        }
//
//        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());
//
//        if (theUser == null) {
//            errors.rejectValue("username", "user.invalid", "The given username does not exist");
//            model.addAttribute("title", "Log In");
//            return "login";
//        }
//
//        String password = loginFormDTO.getPassword();
//
//        if (!theUser.isMatchingPassword(password)) {
//            errors.rejectValue("password", "password.invalid", "Invalid password");
//            model.addAttribute("title", "Log In");
//            return "login";
//        }
//
//        setUserInSession(request.getSession(), theUser);
//
//        return "redirect:";
//    }

}
