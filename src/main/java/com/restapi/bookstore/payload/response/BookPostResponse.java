package com.restapi.bookstore.payload.response;

import com.restapi.bookstore.model.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookPostResponse {

    private String isbn;
    private String title;
    private String description;
    private int pages;
    private String author;
    private Category category;

}
