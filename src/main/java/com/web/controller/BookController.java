package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class BookController {

    @GetMapping("/") 
    public String index() {
        return "index";
    }

    @GetMapping("/about") 
    public String about() {
        return "about"; 
    }

    @GetMapping("/books") 
    public String books() {
        return "books"; 
    }

    @GetMapping("/ebooks") 
    public String ebooks() {
        return "ebooks"; 
    }

    @GetMapping("/catalog") 
    public String catalog() {
        return "catalog"; 
    }

    @GetMapping("/favourite") 
    public String favourite() {
        return "favourite"; 
    }

    @GetMapping("/account") 
    public String account() {
        return "account"; 
    }
    @GetMapping("/login")
    public String loginPage() {
        return "public/authorization/login-page"; // без .html — Thymeleaf сам найдёт
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "public/authorization/registration-page";
    }
}