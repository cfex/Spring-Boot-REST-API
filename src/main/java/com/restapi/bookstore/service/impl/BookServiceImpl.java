package com.restapi.bookstore.service.impl;

import com.restapi.bookstore.model.book.Book;
import com.restapi.bookstore.model.category.Category;
import com.restapi.bookstore.payload.request.BookPostRequest;
import com.restapi.bookstore.payload.response.BookPostResponse;
import com.restapi.bookstore.payload.response.PageableResponse;
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

import static com.restapi.bookstore.utils.ApplicationUtilities.generateISBN;

@Slf4j
@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public BookPostResponse save(BookPostRequest bookRequest, UserPrincipal currentUser) {
        //TODO Implement Spring Security
        Category category = categoryRepository.findById(bookRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("No category found!"));

        Book book = Book.builder()
                .isbn(generateISBN())
                .title(bookRequest.getTitle())
                .description(bookRequest.getDescription())
                .pages(bookRequest.getPages())
                .author(bookRequest.getAuthor())
                .cover(bookRequest.getCover())
                .category(category)
                .build();

        Book addedBook = bookRepository.save(book);

        return BookPostResponse.builder()
                .isbn(addedBook.getIsbn())
                .title(addedBook.getTitle())
                .description(addedBook.getDescription())
                .pages(addedBook.getPages())
                .author(addedBook.getAuthor())
                .category(addedBook.getCategory())
                .build();
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

        Page<Book> books = bookRepository.findByCategory(category.getId(), pageable);

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
