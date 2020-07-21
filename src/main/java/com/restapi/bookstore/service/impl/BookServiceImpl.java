package com.restapi.bookstore.service.impl;

import com.restapi.bookstore.model.Book;
import com.restapi.bookstore.repository.BookRepository;
import com.restapi.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

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
        Set<Book> authorsBooks = new HashSet<>();

        Set<Book> books =  bookRepository.findAllByAuthorContaining(author);
        if(books.isEmpty()) {
            throw new RuntimeException("Author not found");
        }

        books.iterator().forEachRemaining(authorsBooks::add);
        return authorsBooks;
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
