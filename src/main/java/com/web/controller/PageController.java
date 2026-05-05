package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/catalog")
    public String catalog() {
        return "catalog";
    }

    @GetMapping("/account")
    public String account() {
        return "account";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "public/authorization/login-page";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "public/authorization/registration-page";
    }
}
