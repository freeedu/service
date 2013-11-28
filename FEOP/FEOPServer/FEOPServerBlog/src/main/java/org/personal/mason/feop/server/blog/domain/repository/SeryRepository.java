package org.personal.mason.feop.server.blog.domain.repository;

import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.model.Sery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeryRepository extends JpaRepository<Sery, Long> {

    List<Sery> findByCategory(Category category);

    Page<Sery> findByCategory(Category category, Pageable pageable);

    List<Sery> findByCategory(Category category, Sort sort);

    Page<Sery> findBySeriesNameLike(String seriesname, Pageable pageable);

    List<Sery> findBySeriesName(String seriesname);
}
