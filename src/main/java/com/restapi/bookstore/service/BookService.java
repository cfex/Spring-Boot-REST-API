package com.restapi.bookstore.service;

import com.restapi.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BookService {
    //TODO
    // Page<Book> findAllByISBN(String isbn); Page<Book> findByCategory(String category, Pageable pageable);
    Book save(Book book);
    Page<Book> findAll(Pageable pageable);
    Page<Book> findByTitle(String title, Pageable pageable);
    Page<Book> findByAuthor(String author, Pageable pageable);
    Page<Book> findByDescription(String description, Pageable pageable);
}
