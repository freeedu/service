package org.personal.mason.feop.server.blog.domain.service;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
