package com.restapi.bookstore.payload.request;

import com.restapi.bookstore.model.book.Cover;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookPostRequest {

    private String isbn;
    @NotBlank
    @Size(min = 3, max = 50)
    private String title;

    @NotBlank
    @Size(max = 300)
    private String description;

    @NotBlank
    private int pages;

    @NotBlank
    private String author;

    @NotBlank
    private Cover cover;

    @NotBlank
    private Long categoryId;
}
