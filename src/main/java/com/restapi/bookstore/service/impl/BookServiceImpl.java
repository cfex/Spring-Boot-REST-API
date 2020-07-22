package com.restapi.bookstore.service.impl;

import com.restapi.bookstore.model.Book;
import com.restapi.bookstore.repository.BookRepository;
import com.restapi.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findByCategory(String category, Pageable pageable) {
        return bookRepository.findFirst10BooksByCategoriesOrderByTitleAsc(category, PageRequest.of(0, 10));
    }

    @Override
    public Set<Book> findAllByISBN(String isbn) {
        return null;
    }

    @Override
    public Set<Book> findByAuthor(String author) {
        Set<Book> authorsBooks = new HashSet<>();

        Set<Book> books =  bookRepository.findAllByAuthorIgnoreCaseContaining(author);
        if(books.isEmpty()) {
            throw new RuntimeException("No books were found");
        }

        books.iterator().forEachRemaining(authorsBooks::add);
        return authorsBooks;
    }

    @Override
    public Page<Book> findByTitle(String title, Pageable pageable) {
        return bookRepository.findAllByTitleIgnoreCaseContaining(title, pageable);
    }

    @Override
    public Set<Book> findByDescription(String description) {
        return bookRepository.findAllByDescriptionIgnoreCaseContaining(description);
    }
}
