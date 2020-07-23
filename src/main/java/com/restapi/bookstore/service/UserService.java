package com.restapi.bookstore.service;

import com.restapi.bookstore.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> findAllByTitle(String title, Pageable pageable);
}
