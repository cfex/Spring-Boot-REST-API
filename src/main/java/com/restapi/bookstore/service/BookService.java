package com.restapi.bookstore.service;

import com.restapi.bookstore.model.Book;
import com.restapi.bookstore.payload.BookPostRequest;
import com.restapi.bookstore.payload.BookPostResponse;
import com.restapi.bookstore.payload.PageableResponse;
import com.sun.security.auth.UserPrincipal;


public interface BookService {
    //TODO
    // Page<Book> findAllByISBN(String isbn);

    BookPostResponse save(BookPostRequest book, UserPrincipal currentUser);
    PageableResponse<Book> findAll(int page, int size);
    PageableResponse<Book> findByTitle(String title, int page, int size);
    PageableResponse<Book> findByAuthor(String author, int page, int size);
    PageableResponse<Book> findByCategory(Long id, int page, int size);
    PageableResponse<Book> findByDescription(String description, int page, int size);
}
