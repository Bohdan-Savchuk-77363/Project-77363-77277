package com.web.controller;

import com.web.service.FormUiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String loginPage(Model model) {
        model.addAttribute("logFields", FormUiService.getLoginFields());
        return "public/authorization/login-page";
    }
    @GetMapping("/user-information")
    public String informationPage(){
        return "public/authorization/user-information";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("regFields", FormUiService.getRegistrationFields());
        return "public/authorization/registration-page";
    }
}
