package com.restapi.bookstore.repository;

import com.restapi.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {
    //TODO check if can search with just a part of isbn (eg 13115, 332)
    Set<Book> findAllByIsbn(String isbn);

    Set<Book> findAllByAuthorContaining(String author);

    Set<Book> findAllByTitleContaining(String title);
    Set<Book> findAllByDescriptionIsContaining(String description);
}
