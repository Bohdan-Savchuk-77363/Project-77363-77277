package com.web.controller;

import com.web.entity.User;
import com.web.entity.UserProfile;
import com.web.repository.UserProfileRepository;
import com.web.repository.UserRepository;
import com.web.service.FormsUiService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class PageController {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public PageController(UserRepository userRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @GetMapping("/account")
    public String account(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmailIgnoreCase(email).orElse(null);
        if (user != null) {
            model.addAttribute("userName", user.getName());
            UserProfile profile = userProfileRepository.findByUserId(user.getId()).orElse(null);

                model.addAttribute("profile", profile);

        }
        return "account";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/catalog")
    public String catalog() {
        return "catalog";
    }


    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("logFields", FormsUiService.getLoginFields());
        return "public/authorization/login-page";
    }
    @GetMapping("/user-information")

    public String informationPage(Model model){
        model.addAttribute("infoFields", FormsUiService.getProfileFields());
        return "public/authorization/user-information";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("regFields", FormsUiService.getRegistrationFields());
        return "public/authorization/registration-page";
    }
}
