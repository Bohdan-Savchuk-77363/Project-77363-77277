package com.web.controller;

import com.web.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class BookController {

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String bookData(Model model) {
        model.addAttribute("books", BookSe.getA)

        return "index";
    }
}