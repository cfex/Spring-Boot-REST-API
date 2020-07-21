package com.restapi.bookstore.service;

import com.restapi.bookstore.model.Book;

import java.util.Set;

public interface BookService {
    Book save(Book book);
    Set<Book> findAll();
    Set<Book> findAllByISBN(String isbn);
    Set<Book> findByAuthor(String author);
    Set<Book> findByTitle(String title);
    Set<Book> findByDescription(String description);
}
