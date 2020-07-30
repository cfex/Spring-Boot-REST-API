package com.restapi.bookstore.payload.request;

import com.restapi.bookstore.model.book.Cover;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookPostRequest {

    private String isbn;
    private String title;
    private String description;
    private int pages;
    private String author;
    private Cover cover;

    @NotNull
    private Long categoryId;
}
