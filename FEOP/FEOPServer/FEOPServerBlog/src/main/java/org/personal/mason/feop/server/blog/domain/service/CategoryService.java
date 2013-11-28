package org.personal.mason.feop.server.blog.domain.service;

import org.personal.mason.feop.server.blog.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    void save(Category category);

    Category update(Category category);

    List<Category> findByCategory(Category category);

    List<Category> findByCategoryIsNull();

    Page<Category> findByCategory(Category category, Pageable pageable);

    Page<Category> findByCategoryIsNull(Pageable pageable);

    void delete(Category category);

    void delete(Long id);

    Category findById(Long categoryId);

    Category findByCategoryName(String categoryName);
}
