package com.restapi.bookstore.service.impl;

import com.restapi.bookstore.model.category.Category;
import com.restapi.bookstore.payload.request.CategoryPostRequest;
import com.restapi.bookstore.payload.response.CategoryPostResponse;
import com.restapi.bookstore.payload.response.HttpResponse;
import com.restapi.bookstore.payload.response.PageableResponse;
import com.restapi.bookstore.repository.CategoryRepository;
import com.restapi.bookstore.security.UserPrincipal;
import com.restapi.bookstore.service.CategoryService;
import com.restapi.bookstore.utils.ApplicationConstants;
import com.restapi.bookstore.utils.ApplicationUtilities;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.restapi.bookstore.utils.ApplicationUtilities.isUserAdmin;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryPostResponse saveCategory(CategoryPostRequest requestCategory, UserPrincipal currentUser) {
        if (isUserAdmin(currentUser)) {
            Category category = Category.builder()
                    .title(requestCategory.getTitle())
                    .build();

            Category savedCategory = categoryRepository.save(category);

            return CategoryPostResponse.builder()
                    .title(savedCategory.getTitle())
                    .build();
        }

        throw new RuntimeException("Failed");
    }

    @Override
    public PageableResponse<Category> findAllCategories(int page, int size) {

        ApplicationUtilities.validateRequestPageAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, ApplicationConstants.CREATED_AT);
        Page<Category> categories = categoryRepository.findAll(pageable);
        List<Category> content = categories.getNumberOfElements() == 0 ? Collections.emptyList() : categories.getContent();

        return new PageableResponse<>(content,
                categories.getNumber(),
                categories.getSize(),
                categories.getTotalElements(),
                categories.getTotalPages());
    }

    @Override
    public Category updateCategory(Long id, CategoryPostRequest requestCategory, UserPrincipal currentUser) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No category found"));

        if (isUserAdmin(currentUser)) {
            category.setTitle(requestCategory.getTitle());

            return categoryRepository.save(category);
        }

        HttpResponse response = new HttpResponse(Boolean.FALSE, "You don't have permission to update book");

        throw new RuntimeException(response.getMessage());
    }

    @Override
    public HttpResponse deleteCategory(Long id, UserPrincipal currentUser) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No category found"));

        if (isUserAdmin(currentUser)) {
            categoryRepository.delete(category);
            return new HttpResponse(Boolean.TRUE, "You successfully removed category: " + category.getTitle());
        }

        HttpResponse response = new HttpResponse(Boolean.FALSE, "You don't have permission to update book");

        throw new RuntimeException(response.getMessage());
    }
}
