package com.web.controller;

import com.web.service.GoogleBooksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    private final GoogleBooksService googleBooksService;

    public BookController(GoogleBooksService googleBooksService) {
        this.googleBooksService = googleBooksService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", googleBooksService.getDefaultBooks());
        model.addAttribute("query", "");
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "q", defaultValue = "") String query, Model
            model) {
        if (query.isBlank()) {
            return "redirect:/";
        }
        model.addAttribute("books", googleBooksService.searchBooks(query));
        model.addAttribute("query", query);
        return "index";
    }

    @GetMapping("/book/{id}")
    public String bookDetails(@PathVariable String id, Model model) {
        com.web.entity.Book book = googleBooksService.getBookById(id);
        if (book == null) {
            return "redirect:/";
        }
        model.addAttribute("book", book);
        return "book-page";
    }
}