package com.restapi.bookstore.service.impl;

import com.restapi.bookstore.model.user.User;
import com.restapi.bookstore.repository.UserRepository;
import com.restapi.bookstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
    return userRepository.findAll();
    }
}
