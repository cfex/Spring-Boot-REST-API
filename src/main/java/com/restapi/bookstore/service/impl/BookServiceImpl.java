package com.restapi.bookstore.service.impl;

import com.restapi.bookstore.model.Book;
import com.restapi.bookstore.repository.BookRepository;
import com.restapi.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Set<Book> findAll() {
        return new HashSet<>(bookRepository.findAll());
    }

    @Override
    public Set<Book> findAllByISBN(String isbn) {
        return null;
    }

    @Override
    public Set<Book> findByAuthor(String author) {
        return null;
    }

    @Override
    public Set<Book> findByTitle(String title) {
        return bookRepository.findAllByTitleContaining(title);
    }

    @Override
    public Set<Book> findByDescription(String description) {
        return bookRepository.findAllByDescriptionIsContaining(description);
    }
}
