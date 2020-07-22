package com.restapi.bookstore.repository;

import com.restapi.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {
    Page<Book> findFirst10BooksByCategoriesOrderByTitleAsc(String category, Pageable pageable);
    Set<Book> findAllByAuthorIgnoreCaseContaining(String author);
    Page<Book> findAllByTitleIgnoreCaseContaining(String title, Pageable pageable);
    Set<Book> findAllByDescriptionIgnoreCaseContaining(String description);
    Page<Book> findAll(Pageable pageable);
}
