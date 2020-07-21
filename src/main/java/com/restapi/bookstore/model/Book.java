package com.restapi.bookstore.model;

import com.restapi.bookstore.config.Audit;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends Audit<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "pages")
    private int pages;

    @Column(name = "author")
    private String author;

    @Enumerated(value = EnumType.STRING)
    private Cover cover;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();
}
