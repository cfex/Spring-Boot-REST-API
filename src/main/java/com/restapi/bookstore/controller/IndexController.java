package com.restapi.bookstore.controller;

import com.restapi.bookstore.model.Book;
import com.restapi.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class IndexController {

    private final BookService bookService;

    @GetMapping(path = "/")
    public ResponseEntity<Page<Book>> index(Pageable pageable) {
        //TODO check for empty set

       return ResponseEntity.status(HttpStatus.OK)
               .body(bookService.findAll(pageable));
    }
}
