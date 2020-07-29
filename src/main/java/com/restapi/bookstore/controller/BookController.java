package com.restapi.bookstore.controller;

import com.restapi.bookstore.model.Book;
import com.restapi.bookstore.payload.BookPostRequest;
import com.restapi.bookstore.payload.PageableResponse;
import com.restapi.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.restapi.bookstore.utils.RequestConstants.DEFAULT_PAGE_NUMBER;
import static com.restapi.bookstore.utils.RequestConstants.DEFAULT_PAGE_SIZE;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<PageableResponse<Book>> findAll(
            @RequestParam(value = "page", required = false,
                    defaultValue = DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false,
                    defaultValue = DEFAULT_PAGE_SIZE) int size) {
        PageableResponse<Book> books = bookService.findAll(page, size);

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Book> saveBook(@Validated @RequestBody BookPostRequest requestBook) {
        return null;
    }


    @GetMapping("/findByAuthor")
    public ResponseEntity<PageableResponse<Book>> findByAuthor(@RequestParam("author") String author,
                                                               @RequestParam(value = "page", required = false,
                                                                       defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                               @RequestParam(value = "size", required = false,
                                                                       defaultValue = DEFAULT_PAGE_SIZE) int size) {

       PageableResponse<Book> books = bookService.findByAuthor(author, page, size);

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/findByTitle")
    public ResponseEntity<PageableResponse<Book>> findByTitle(@RequestParam(name = "title") String title,
                                                              @RequestParam(value = "page", required = false,
                                                                      defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                              @RequestParam(value = "size", required = false,
                                                                      defaultValue = DEFAULT_PAGE_SIZE) int size) {

        PageableResponse<Book> books = bookService.findByTitle(title, page, size);

        return  new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/findByCategory/{id}")
    public ResponseEntity<PageableResponse<Book>> findByCategory(@PathVariable(name = "id") Long id,
                                                                 @RequestParam(value = "page", required = false,
                                                                         defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                                 @RequestParam(value = "size", required = false,
                                                                         defaultValue = DEFAULT_PAGE_SIZE) int size){
        PageableResponse<Book> books = bookService.findByCategory(id, page, size);

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/findByDesc")
    public ResponseEntity<PageableResponse<Book>> findByDesc(@RequestParam(required = false) String desc,
                                                             @RequestParam(value = "page", required = false,
                                                                     defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                             @RequestParam(value = "size", required = false,
                                                                     defaultValue = DEFAULT_PAGE_SIZE) int size){
        PageableResponse<Book> books = bookService.findByDescription(desc, page, size);

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
