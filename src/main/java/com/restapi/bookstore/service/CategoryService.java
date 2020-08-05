package com.restapi.bookstore.service;

import com.restapi.bookstore.model.category.Category;

import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(Long id);
}
