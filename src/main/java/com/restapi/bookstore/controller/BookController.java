package com.restapi.bookstore.controller;

import com.restapi.bookstore.model.Book;
import com.restapi.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;

    @GetMapping(path = "/findAll")
    public ResponseEntity<Set<Book>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Book> saveBook(@RequestBody Book requestBook) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.save(requestBook));
    }


    @GetMapping("/findByAuthor/{author}")
    public ResponseEntity<Set<Book>> findByAuthor(@PathVariable String author) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.findByAuthor(author));
    }
}
