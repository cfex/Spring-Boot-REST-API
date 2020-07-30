package com.restapi.bookstore.model.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.bookstore.model.book.Book;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String title;

    @OneToMany(mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private Set<Book> books;

    @Override
    public String toString() {
        return title;
    }
}
