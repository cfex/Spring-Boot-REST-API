package com.restapi.bookstore.service;

import com.restapi.bookstore.model.user.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
}
