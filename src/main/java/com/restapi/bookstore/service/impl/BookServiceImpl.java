package com.restapi.bookstore.service.impl;

import com.restapi.bookstore.exceptions.ResourceNotFoundException;
import com.restapi.bookstore.exceptions.UnauthorizedRequestException;
import com.restapi.bookstore.model.book.Book;
import com.restapi.bookstore.model.category.Category;
import com.restapi.bookstore.model.user.User;
import com.restapi.bookstore.payload.request.BookPostRequest;
import com.restapi.bookstore.payload.response.BookPostResponse;
import com.restapi.bookstore.payload.response.HttpResponse;
import com.restapi.bookstore.payload.response.PageableResponse;
import com.restapi.bookstore.repository.BookRepository;
import com.restapi.bookstore.repository.CategoryRepository;
import com.restapi.bookstore.repository.UserRepository;
import com.restapi.bookstore.security.UserPrincipal;
import com.restapi.bookstore.service.BookService;
import com.restapi.bookstore.utils.ApplicationConstants;
import com.restapi.bookstore.utils.ApplicationUtilities;
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
import static com.restapi.bookstore.utils.ApplicationUtilities.isUserAdmin;

@Slf4j
@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public PageableResponse<Book> findAll(int page, int size) {
        ApplicationUtilities.validateRequestPageAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, ApplicationConstants.CREATED_AT);

        Page<Book> books = bookRepository.findAll(pageable);
        List<Book> content = books.getNumberOfElements() == 0 ? Collections.emptyList() : books.getContent();

        return new PageableResponse<>(content,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages());
    }

    @Override
    public PageableResponse<Book> findAllByISBN(String isbn, int page, int size) {
        ApplicationUtilities.validateRequestPageAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, ApplicationConstants.CREATED_AT);

        Page<Book> books = bookRepository.findAllByIsbnContainingIgnoreCase(isbn, pageable);
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

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, ApplicationConstants.CREATED_AT);

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
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, ApplicationConstants.CREATED_AT);

        Page<Book> books = bookRepository.findAllByAuthorContainingIgnoreCase(author, pageable);
        List<Book> content = books.getNumberOfElements() == 0 ? Collections.emptyList() : books.getContent();

        return new PageableResponse<>(content,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages());
    }


    @Override
    public PageableResponse<Book> findByCategory(Long id, int page, int size)  {
        ApplicationUtilities.validateRequestPageAndSize(page, size);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        System.out.println(category.getTitle());
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, ApplicationConstants.CREATED_AT);

        Page<Book> books = bookRepository.findByCategory_Id(category.getId(), pageable);

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
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, ApplicationConstants.CREATED_AT);

        Page<Book> books = bookRepository.findAllByDescriptionContainingIgnoreCase(description, pageable);
        List<Book> content = books.getNumberOfElements() == 0 ? Collections.emptyList() : books.getContent();

        return new PageableResponse<>(content,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages());
    }

    @Override
    public BookPostResponse save(BookPostRequest bookRequest, UserPrincipal currentUser) {

        Category category = categoryRepository.findById(bookRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category with id " + bookRequest.getCategoryId() + " not found"));

        User user = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new UnauthorizedRequestException("Unauthorized Request. Please sign up or log in"));

        Book book = Book.builder()
                .isbn(generateISBN())
                .title(bookRequest.getTitle())
                .description(bookRequest.getDescription())
                .pages(bookRequest.getPages())
                .author(bookRequest.getAuthor())
                .cover(bookRequest.getCover())
                .category(category)
                .user(user)
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
    public HttpResponse removeBook(Long id, UserPrincipal currentUser) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Book with id " + id + " not found"));

        if (book.getUser().getId().equals(currentUser.getId()) || isUserAdmin(currentUser)) {

            bookRepository.delete(book);
            return new HttpResponse(Boolean.TRUE, "You successfully deleted book!");
        }

        HttpResponse response = new HttpResponse(Boolean.FALSE, "You don't have permission to update book");

        throw new RuntimeException(response.getMessage());
    }

    @Override
    public Book updateBook(Long id, BookPostRequest requestBook, UserPrincipal currentUser) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Book with id " + id + " not found"));

        Category category = categoryRepository.findById(requestBook.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category with id " + requestBook.getCategoryId() + " not found"));

        if (book.getUser().getId().equals(currentUser.getId())
                || isUserAdmin(currentUser)) {

            book.setIsbn(requestBook.getIsbn());
            book.setTitle(requestBook.getTitle());
            book.setDescription(requestBook.getDescription());
            book.setPages(requestBook.getPages());
            book.setAuthor(requestBook.getAuthor());
            book.setCover(requestBook.getCover());
            book.setCategory(category);

            return bookRepository.save(book);
        }

        HttpResponse response = new HttpResponse(Boolean.FALSE, "You don't have permission to update book");

        throw new RuntimeException(response.getMessage());
    }

}
