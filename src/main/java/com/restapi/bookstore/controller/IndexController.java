package com.restapi.bookstore.controller;


import com.restapi.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class IndexController {

    private final BookService bookService;

    @GetMapping(path = "/")
    public String index(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "index";
    }
}
