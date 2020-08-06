package com.restapi.bookstore.payload.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryPostRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String title;
}
