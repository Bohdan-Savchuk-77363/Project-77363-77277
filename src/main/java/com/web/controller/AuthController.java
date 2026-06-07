package com.web.controller;

import com.web.entity.User;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            return "public/authorization/registration-page"; // назад с ошибкой
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model){
        try{
            User user = new User();
            user.setEmail(email);
//            user.setName();
            user.setPassword(password);
            userService.logginig(user, password);
            User loggedUser = userService.logginig(user, password);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loggedUser.getName(), null, List.of());
            SecurityContextHolder.getContext().setAuthentication(auth);

            return "/account";
        }catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            return "public/authorization/login-page";
        }
    }




    }
