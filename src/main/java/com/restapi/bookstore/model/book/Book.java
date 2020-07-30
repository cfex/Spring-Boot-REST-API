package com.restapi.bookstore.model.book;

import com.restapi.bookstore.config.Audit;
import com.restapi.bookstore.model.category.Category;
import com.restapi.bookstore.model.user.User;
import lombok.*;

import javax.persistence.*;

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

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "pages")
    private int pages;

    @Column(name = "author")
    private String author;

    @Enumerated(value = EnumType.STRING)
    private Cover cover;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
