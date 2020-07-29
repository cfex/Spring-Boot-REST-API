package com.restapi.bookstore.service.impl;

import com.restapi.bookstore.model.Book;
import com.restapi.bookstore.model.Category;
import com.restapi.bookstore.payload.BookPostRequest;
import com.restapi.bookstore.payload.BookPostResponse;
import com.restapi.bookstore.payload.PageableResponse;
import com.restapi.bookstore.repository.BookRepository;
import com.restapi.bookstore.repository.CategoryRepository;
import com.restapi.bookstore.service.BookService;
import com.restapi.bookstore.utils.ApplicationConstants;
import com.restapi.bookstore.utils.ApplicationUtilities;
import com.sun.security.auth.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public BookPostResponse save(BookPostRequest book, UserPrincipal currentUser) {
        //TODO Implement Spring Security
        return null;
    }

    @Override
    public PageableResponse<Book> findAll(int page, int size) {
        ApplicationUtilities.validateRequestPageAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, ApplicationConstants.CREATED_BY);
        Page<Book> books = bookRepository.findAll(pageable);
        List<Book> content = books.getNumberOfElements() == 0 ? Collections.emptyList() : books.getContent();

        return new PageableResponse<>(content,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages());
    }

    @Override
    public PageableResponse<Book> findByTitle(String title, int page, int size) {
        ApplicationUtilities.validateRequestPageAndSize(page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, ApplicationConstants.CREATED_BY);

        Page<Book> books = bookRepository.findAllByTitleContainingIgnoreCase(title, pageable);
        List<Book> content = books.getNumberOfElements() == 0 ? Collections.emptyList() : books.getContent();

        return new PageableResponse<>(content,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages());
    }

    @Override
    public PageableResponse<Book> findByAuthor(String author, int page, int size) {
        ApplicationUtilities.validateRequestPageAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, ApplicationConstants.CREATED_BY);

        Page<Book> books = bookRepository.findAllByAuthorContainingIgnoreCase(author, pageable);
        List<Book> content = books.getNumberOfElements() == 0 ? Collections.emptyList() : books.getContent();

        return new PageableResponse<>(content,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages());
    }


    @Override
    public PageableResponse<Book> findByCategory(Long id, int page, int size) {
        ApplicationUtilities.validateRequestPageAndSize(page, size);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No category found"));

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, ApplicationConstants.CREATED_BY);

        Page<Book> books = bookRepository.findByCategories(category.getId(), pageable);

        List<Book> content = books.getNumberOfElements() == 0 ? Collections.emptyList() : books.getContent();

        return new PageableResponse<>(content,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages());
    }

    @Override
    public PageableResponse<Book> findByDescription(String description, int page, int size) {
        ApplicationUtilities.validateRequestPageAndSize(page, size);
        Sort sort;
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, ApplicationConstants.CREATED_BY);

        Page<Book> books = bookRepository.findAllByDescriptionContainingIgnoreCase(description, pageable);
        List<Book> content = books.getNumberOfElements() == 0 ? Collections.emptyList() : books.getContent();

        return new PageableResponse<>(content,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages());
    }

}
