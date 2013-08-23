package org.personal.mason.feop.server.blog.domain.repository;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.model.Sery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.String;

public interface SeryRepository extends JpaRepository<Sery, Long> {

	List<Sery> findByCategory(Category category);

	List<Sery> findByCategory(Category category, Pageable pageable);

	List<Sery> findByCategory(Category category, Sort sort);

	List<Sery> findBySeriesNameLike(String seriesname, Pageable pageable);

}
