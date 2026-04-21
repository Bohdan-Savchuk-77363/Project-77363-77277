package com.web.project7736377277;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Важно: для работы с шаблонами из /templates
public class ProjectController {

    @GetMapping("/") // Будет открываться по адресу http://localhost:8080/
    public String firstPage() {
        return "index"; // Spring сам найдет src/main/resources/templates/index.html
    }

    @GetMapping("/about") // Будет открываться по адресу http://localhost:8080/about
    public String aboutPage() {
        return "about"; // Для файла about.html
    }
}