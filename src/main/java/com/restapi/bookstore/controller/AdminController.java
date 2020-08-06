package com.restapi.bookstore.controller;

import com.restapi.bookstore.model.user.User;
import com.restapi.bookstore.payload.response.HttpResponse;
import com.restapi.bookstore.payload.response.PageableResponse;
import com.restapi.bookstore.security.CurrentlyLogged;
import com.restapi.bookstore.security.UserPrincipal;
import com.restapi.bookstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.restapi.bookstore.utils.RequestConstants.DEFAULT_PAGE_NUMBER;
import static com.restapi.bookstore.utils.RequestConstants.DEFAULT_PAGE_SIZE;

@AllArgsConstructor
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    @GetMapping("/listAllUsers")
    public ResponseEntity<PageableResponse<User>> listAllUsers(@RequestParam(value = "page", required = false,
            defaultValue = DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "size", required = false,
            defaultValue = DEFAULT_PAGE_SIZE) int size) {
        PageableResponse<User> users = userService.listAll(page, size);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User requestUser) {
        User user = userService.createUser(requestUser);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("id") Long id,
                                                   @CurrentlyLogged UserPrincipal currentUser) {
        HttpResponse response = userService.deleteUser(id, currentUser);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


