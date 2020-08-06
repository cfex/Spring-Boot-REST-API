package com.restapi.bookstore.controller;

import com.restapi.bookstore.payload.request.RegisterUserRequest;
import com.restapi.bookstore.payload.response.HttpResponse;
import com.restapi.bookstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<HttpResponse> registerUser(@Valid @RequestBody RegisterUserRequest requestUser) {
        HttpResponse response = userService.registerUser(requestUser);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
