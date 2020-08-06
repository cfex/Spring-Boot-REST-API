package com.restapi.bookstore.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Setter
@Getter
public class RegisterUserRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20)
    private String lastName;

    @NotBlank
    @Size(min = 4, max = 15)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    private String password;

}
