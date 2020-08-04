package com.restapi.bookstore.controller;

import com.restapi.bookstore.model.user.User;
import com.restapi.bookstore.payload.response.CredentialsAvailability;
import com.restapi.bookstore.payload.response.LoggedUserResponse;
import com.restapi.bookstore.payload.response.UserProfileResponse;
import com.restapi.bookstore.security.CurrentlyLogged;
import com.restapi.bookstore.security.UserPrincipal;
import com.restapi.bookstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<LoggedUserResponse> myProfile(@CurrentlyLogged UserPrincipal user){
        LoggedUserResponse loggedUser = userService.getCurrentUser(user);

        return new ResponseEntity<>(loggedUser, HttpStatus.OK);
    }

    @GetMapping("/checkUsernameAvailability")
    public ResponseEntity<CredentialsAvailability> checkUsernameAvailability(@RequestParam(value = "username")
                                                                                         String username){
        CredentialsAvailability availability = userService.checkUsernameAvailability(username);

        return new ResponseEntity<>(availability, HttpStatus.OK);
    }

    @GetMapping("/checkEmailAvailability")
    public ResponseEntity<CredentialsAvailability> checkEmailAvailability(@RequestParam(value = "email") String email){
        CredentialsAvailability availability = userService.checkEmailAvailability(email);

        return new ResponseEntity<>(availability, HttpStatus.OK);
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<UserProfileResponse> showUserProfile(@PathVariable("username") String username) {
        UserProfileResponse userProfile = userService.showUserProfile(username);

        return new ResponseEntity<>(userProfile, HttpStatus.FOUND);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createUser(@Validated @RequestBody User requestUser) {
        User user = userService.createUser(requestUser);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/update/{username}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<User> updateUser(@Validated @RequestBody User requestUser,
                                           @PathVariable(value = "username") String username, @CurrentlyLogged UserPrincipal currentUser) {

        User updatedUser = userService.updateUser(requestUser, username, currentUser);

        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }
}
