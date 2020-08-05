package com.restapi.bookstore.model.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.bookstore.config.Audit;
import com.restapi.bookstore.model.book.Book;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String title;

    @OneToMany(mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private List<Book> books;

    public List<Book> getBooks() {
        return this.books == null ? null : new ArrayList<>(this.books);
    }

    public void setBooks(List<Book> books) {
        if(books == null) {
            this.books = null;
        }else {
            this.books = Collections.unmodifiableList(books);
        }
    }

    @Override
    public String toString() {
        return title;
    }
}
