package com.restapi.bookstore.repository;

import com.restapi.bookstore.model.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
}
