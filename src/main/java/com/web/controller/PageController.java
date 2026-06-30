package com.web.controller;

import com.web.entity.Book;
import com.web.entity.User;
import com.web.entity.UserProfile;
import com.web.repository.UserProfileRepository;
import com.web.repository.UserRepository;
import com.web.service.FormsUiService;
import com.web.service.GoogleBooksService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class PageController {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final GoogleBooksService googleBooksService;

    @Value("${google.books.default.maxResults}")
    private int pageSize;

    public PageController(UserRepository userRepository, UserProfileRepository userProfileRepository,
                          GoogleBooksService googleBooksService) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.googleBooksService = googleBooksService;
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
    public String catalog(@RequestParam(name = "q", defaultValue = "") String query,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          Model model) {
        if (query.isBlank()) {
            query = "fiction";
        }
        Map<String, Object> result = googleBooksService.fetchBooksWithTotal(query, pageSize, page * pageSize);
        List<Book> books = (List<Book>) result.get("books");
        int totalItems = (int) result.get("totalItems");
        int totalPages = Math.min((int) Math.ceil((double) totalItems / pageSize), 20);

        model.addAttribute("books", books);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("query", query);
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
