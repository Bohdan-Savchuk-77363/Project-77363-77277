package com.web.controller;

import com.web.entity.User;
import com.web.entity.UserProfile;
import com.web.repository.UserProfileRepository;
import com.web.repository.UserRepository;
import com.web.service.FormsUiService;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserService userService, UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userService = userService;
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }


    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        try {

            userService.register(user);
            return "redirect:/login"; // после регистрации → на логин

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("regFields", FormsUiService.getRegistrationFields());
            return "public/authorization/registration-page"; // назад с ошибкой
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            User user = new User();
            user.setEmail(email);
//            user.setName();
            user.setPassword(password);
            userService.logginig(user, password);
            User loggedUser = userService.logginig(user, password);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loggedUser.getEmail(), null, List.of());
            SecurityContextHolder.getContext().setAuthentication(auth);

            return "redirect:/account";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("logFields", FormsUiService.getLoginFields());
            return "public/authorization/login-page";
        }
    }

    @PostMapping("/update")
    public String update(@RequestParam int age, @RequestParam String country, @RequestParam(required = false) String photoUrl, Model model) {
        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String currentEmail = auth.getName();


            User currentUser = userRepository.findByEmailIgnoreCase(currentEmail)
                    .orElseThrow(() -> new RuntimeException("User not fount!!!"));
//            UserProfile userProfile = userProfileRepository.findByUserId(currentUser.getId())
//                    .orElseThrow(() -> new RuntimeException("User not fount!!!"));

            UserProfile userProfile = userProfileRepository.findByUserId(currentUser.getId())
                           .orElseGet(() -> {
                            UserProfile newProfile = new UserProfile();
                            newProfile.setUser(currentUser);
                            return newProfile;
                        });
            userProfile.setAge(age);
            userProfile.setCountry(country);
            userProfile.setPhotoUrl(photoUrl);


            userProfileRepository.save(userProfile);
            return "redirect:/account";

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("infoFields", FormsUiService.getLoginFields());
        }

    return"public/authorization/user-information";
    }
}