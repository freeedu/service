package org.personal.mason.feop.server.blog.spi;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.Category;

public interface CategoryService {

	void save(Category category);

	Category update(Category category);

	List<Category> findByCategory(Category category);

	List<Category> findByCategoryIsNull();

	List<Category> findByCategory(Category category, int page, int size);

	List<Category> findByCategoryIsNull(int page, int size);

	void delete(Category category);

	void delete(Long id);

	Category findById(Long categoryId);

	Category findByCategoryName(String categoryName);
}
