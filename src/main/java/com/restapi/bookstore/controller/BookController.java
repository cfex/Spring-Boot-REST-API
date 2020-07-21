package com.restapi.bookstore.controller;

import com.restapi.bookstore.model.Book;
import com.restapi.bookstore.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/books")
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookRepository.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Book> saveBook(@RequestBody Book requestBook) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookRepository.save(requestBook));
    }
}
