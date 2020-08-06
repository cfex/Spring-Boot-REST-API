package com.restapi.bookstore.service;

import com.restapi.bookstore.model.category.Category;
import com.restapi.bookstore.payload.request.CategoryPostRequest;
import com.restapi.bookstore.payload.response.CategoryPostResponse;
import com.restapi.bookstore.payload.response.HttpResponse;
import com.restapi.bookstore.payload.response.PageableResponse;
import com.restapi.bookstore.security.UserPrincipal;

public interface CategoryService {
    CategoryPostResponse saveCategory(CategoryPostRequest requestCategory, UserPrincipal currentUser);

    PageableResponse<Category> findAllCategories(int page, int size);

    Category updateCategory(Long id, CategoryPostRequest requestCategory, UserPrincipal currentUser);

    HttpResponse deleteCategory(Long id, UserPrincipal currentUser);
}
