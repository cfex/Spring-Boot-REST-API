package com.restapi.bookstore.service;

import com.restapi.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface BookService {
    Book save(Book book);
    Page<Book> findAll(Pageable pageable);
    Page<Book> findByCategory(String category, Pageable pageable);
    Set<Book> findAllByISBN(String isbn);
    Set<Book> findByAuthor(String author);
    Page<Book> findByTitle(String title, Pageable pageable);
    Set<Book> findByDescription(String description);
}
