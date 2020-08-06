package com.restapi.bookstore.controller;

import com.restapi.bookstore.model.category.Category;
import com.restapi.bookstore.payload.request.CategoryPostRequest;
import com.restapi.bookstore.payload.response.CategoryPostResponse;
import com.restapi.bookstore.payload.response.HttpResponse;
import com.restapi.bookstore.payload.response.PageableResponse;
import com.restapi.bookstore.security.CurrentlyLogged;
import com.restapi.bookstore.security.UserPrincipal;
import com.restapi.bookstore.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<PageableResponse<Category>> findAll(
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) int size) {

        PageableResponse<Category> response = categoryService.findAllCategories(page, size);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryPostResponse> saveCategory(@Valid @RequestBody CategoryPostRequest categoryRequest,
                                                             @CurrentlyLogged UserPrincipal currentUser) {

        CategoryPostResponse response = categoryService.saveCategory(categoryRequest, currentUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id,
                                                   @Valid @RequestBody CategoryPostRequest requestCategory,
                                                   @CurrentlyLogged UserPrincipal currentUser) {

        Category category = categoryService.updateCategory(id, requestCategory, currentUser);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCategory/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpResponse> deleteCategory(@PathVariable("id") Long id,
                                                       @CurrentlyLogged UserPrincipal currentUser) {

        HttpResponse response = categoryService.deleteCategory(id, currentUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
