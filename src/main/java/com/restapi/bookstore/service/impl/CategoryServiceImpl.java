package com.restapi.bookstore.service.impl;

import com.restapi.bookstore.model.category.Category;
import com.restapi.bookstore.repository.CategoryRepository;
import com.restapi.bookstore.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryServiceImpl  implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }
}
