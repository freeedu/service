package org.personal.mason.feop.server.blog.domain.repository;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByCategory(Category category);

	List<Category> findByCategoryIsNull();

	Page<Category> findByCategory(Category category, Pageable pageable);

	Page<Category> findByCategoryIsNull(Pageable pageable);

	List<Category> findByCategoryName(String categoryname);
}
