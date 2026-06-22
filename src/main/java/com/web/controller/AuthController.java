package com.web.controller;

import com.web.dto.FormField;
import com.web.entity.User;
import com.web.entity.UserProfile;
import com.web.service.FormUiService;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        try {

            userService.register(user);
            return "redirect:/login"; // после регистрации → на логин

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("regFields", FormUiService.getRegistrationFields());
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

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loggedUser.getName(), null, List.of());
            SecurityContextHolder.getContext().setAuthentication(auth);

            return "redirect:/account";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("logFields", FormUiService.getLoginFields());
            return "public/authorization/login-page";
        }
    }

    @PostMapping("/update")
    public String update(@RequestParam int age, @RequestParam String country, @RequestParam String photoUrl, Model model) {
        try {
            UserProfile userProfile = new UserProfile();
            userProfile.setAge(age);
            userProfile.setCountry(country);
            userProfile.setPhotoUrl(photoUrl);
        } catch (IllegalArgumentException e) {
        }

    return"hello";
    }
}