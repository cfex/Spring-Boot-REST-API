package com.restapi.bookstore.service;

import com.restapi.bookstore.model.book.Book;
import com.restapi.bookstore.payload.request.BookPostRequest;
import com.restapi.bookstore.payload.response.BookPostResponse;
import com.restapi.bookstore.payload.response.PageableResponse;
import com.restapi.bookstore.security.UserPrincipal;

;


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
