package com.restapi.bookstore.payload.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryPostRequest {

    private String title;
}
