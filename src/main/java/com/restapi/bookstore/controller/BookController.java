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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@Controller
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{size}")
    public ResponseEntity<Page<Book>> indexBooks(@PathVariable("size") int size) {
        //TODO
        return null;
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<Page<Book>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.findAll(pageable));
    }

    @PostMapping("/save")
    public ResponseEntity<Book> saveBook(@NotNull @RequestBody Book requestBook) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.save(requestBook));
    }


    @GetMapping("/findByAuthor/{author}")
    public ResponseEntity<Page<Book>> findByAuthor(@PathVariable("author") String author) {
        //TODO
        return null;
    }

    @GetMapping("/findByTitle")
    public ResponseEntity<Page<Book>> findByTitle(@RequestParam(required = false) String title,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size) {
        try{
            PageRequest pageable = PageRequest.of(page, size);

            if(title == null) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(bookService.findAll(pageable));
            }

            if(bookService.findByTitle(title, pageable) == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(bookService.findByTitle(title, pageable));

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
