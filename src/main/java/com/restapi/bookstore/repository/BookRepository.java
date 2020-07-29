package com.restapi.bookstore.repository;

import com.restapi.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {
    Page<Book> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Book> findAllByAuthorContainingIgnoreCase(String author, Pageable pageable);
    Page<Book> findByCategories(Long id, Pageable pageable);
    Page<Book> findAllByDescriptionContainingIgnoreCase(String description, Pageable pageable);
}