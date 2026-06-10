package com.web.controller;

import com.web.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String bookData(Model model) {
        System.out.println("Count of page: " + bookService.getAllBook().size());
        model.addAttribute("books", bookService.getAllBook());

        return "index";
    }
}