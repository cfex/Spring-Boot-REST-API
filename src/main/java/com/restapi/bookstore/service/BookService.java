package com.restapi.bookstore.service;

import com.restapi.bookstore.model.book.Book;
import com.restapi.bookstore.payload.request.BookPostRequest;
import com.restapi.bookstore.payload.response.BookPostResponse;
import com.restapi.bookstore.payload.response.HttpResponse;
import com.restapi.bookstore.payload.response.PageableResponse;
import com.restapi.bookstore.security.UserPrincipal;

public interface BookService {
    PageableResponse<Book> findAll(int page, int size);

    PageableResponse<Book> findAllByISBN(String isbn, int page, int size);

    PageableResponse<Book> findByTitle(String title, int page, int size);

    PageableResponse<Book> findByAuthor(String author, int page, int size);

    PageableResponse<Book> findByCategory(Long id, int page, int size);

    PageableResponse<Book> findByDescription(String description, int page, int size);

    BookPostResponse save(BookPostRequest book, UserPrincipal currentUser);

    HttpResponse removeBook(Long id, UserPrincipal currentUser);

    Book updateBook(Long id, BookPostRequest requestBook, UserPrincipal currentUser);
}
