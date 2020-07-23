package com.restapi.bookstore.service.impl;

import com.restapi.bookstore.model.Book;
import com.restapi.bookstore.repository.BookRepository;
import com.restapi.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


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
        return bookRepository.findAllByCategoriesOrderByTitleAsc(category, pageable);
    }

    @Override
    public Page<Book> findAllByISBN(String isbn) {
        return null;
    }

    @Override
    public Page<Book> findByAuthor(String author, Pageable pageable) {
       return bookRepository.findAllByAuthorContainingIgnoreCase(author, pageable);
    }

    @Override
    public Page<Book> findByTitle(String title, Pageable pageable) {
        return bookRepository.findAllByTitleContainingIgnoreCase(title, pageable);
    }


    @Override
    public Page<Book> findByDescription(String description, Pageable pageable) {
        return bookRepository.findAllByDescriptionStartingWithIgnoreCase(description, pageable);
    }
}
