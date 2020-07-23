package com.restapi.bookstore.controller;

import com.restapi.bookstore.model.Book;
import com.restapi.bookstore.service.BookService;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;



@AllArgsConstructor
@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<Book>> findAll(Pageable pageable) {
        if(bookService.findAll(pageable).stream().anyMatch(Objects::isNull)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.findAll(pageable));
    }

    @PostMapping("/save")
    public ResponseEntity<Book> saveBook(@NotNull @RequestBody Book requestBook) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.save(requestBook));
    }


    @GetMapping("/findByAuthor/{author}")
    public ResponseEntity<Page<Book>> findByAuthor(@PathVariable("author") String author,
                                                   @RequestParam(required = false, defaultValue = "0") int page,
                                                   @RequestParam(required = false, defaultValue = "10")int size) {
        try {
            PageRequest pageable = PageRequest.of(page, size);

            if(author == null) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(bookService.findAll(pageable));
            }

            if(bookService.findByAuthor(author, pageable).isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(bookService.findByAuthor(author, pageable));

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("/findByTitle")
    public ResponseEntity<Page<Book>> findByTitle(@RequestParam(required = false) String title,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        try{
            PageRequest pageable = PageRequest.of(page, size);

            if(title == null) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(bookService.findAll(pageable));
            }

            if(bookService.findByTitle(title, pageable).isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(bookService.findByTitle(title, pageable));

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByDesc")
    public ResponseEntity<Page<Book>> findByDesc(@RequestParam(required = false) String desc,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size){

        try{
            PageRequest pageable = PageRequest.of(page, size);

            if(desc == null) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(bookService.findAll(pageable));
            }

            if(bookService.findByDescription(desc, pageable).isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(bookService.findByDescription(desc, pageable));

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
