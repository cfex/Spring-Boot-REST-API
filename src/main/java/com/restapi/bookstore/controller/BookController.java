package com.restapi.bookstore.controller;

import com.restapi.bookstore.model.book.Book;
import com.restapi.bookstore.payload.request.BookPostRequest;
import com.restapi.bookstore.payload.response.BookPostResponse;
import com.restapi.bookstore.payload.response.HttpResponse;
import com.restapi.bookstore.payload.response.PageableResponse;
import com.restapi.bookstore.security.CurrentlyLogged;
import com.restapi.bookstore.security.UserPrincipal;
import com.restapi.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<BookPostResponse> saveBook(@Valid @RequestBody BookPostRequest requestBook, @CurrentlyLogged UserPrincipal currentUser) {
        BookPostResponse books = bookService.save(requestBook, currentUser);

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookPostRequest requestBook, @CurrentlyLogged UserPrincipal currentUser) {
        Book book = bookService.updateBook(id, requestBook, currentUser);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HttpResponse> deleteBook(@PathVariable("id") Long id, @CurrentlyLogged UserPrincipal currentUser) {
        HttpResponse response = bookService.removeBook(id, currentUser);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/findByISBN/{isbn}")
    public ResponseEntity<PageableResponse<Book>> findByISBN(@PathVariable("isbn") String isbn,
                                           @RequestParam(value = "page", required = false,
                                                   defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                           @RequestParam(value = "size", required = false,
                                                   defaultValue = DEFAULT_PAGE_SIZE) int size) {
        PageableResponse<Book> books = bookService.findAllByISBN(isbn, page, size);

        return new ResponseEntity<>(books, HttpStatus.FOUND);
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
