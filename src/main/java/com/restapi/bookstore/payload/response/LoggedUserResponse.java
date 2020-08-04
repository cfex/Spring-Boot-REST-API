package com.restapi.bookstore.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class LoggedUserResponse {

    private String firstName;
    private String lastName;
    private String email;
}
