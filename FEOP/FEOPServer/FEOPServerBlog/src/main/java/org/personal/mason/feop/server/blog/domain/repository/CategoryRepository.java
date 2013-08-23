package org.personal.mason.feop.server.blog.domain.repository;

import org.personal.mason.feop.server.blog.domain.model.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.lang.String;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByCategory(Category category);

	List<Category> findByCategoryIsNull();

	List<Category> findByCategory(Category category, Pageable pageable);

	List<Category> findByCategoryIsNull(Pageable pageable);

	List<Category> findByCategoryName(String categoryname);
}
