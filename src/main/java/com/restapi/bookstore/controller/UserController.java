package com.restapi.bookstore.controller;

import com.restapi.bookstore.model.user.User;
import com.restapi.bookstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @GetMapping("/profile")
    public @ResponseBody String showProfile() {
        return "Authenticated user";
    }

    @GetMapping("/listAll")
    public List<User> showAllUsers() {
        return userService.findAll();
    }
}
