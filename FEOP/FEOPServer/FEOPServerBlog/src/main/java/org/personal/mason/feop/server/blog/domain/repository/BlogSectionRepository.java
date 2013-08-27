package org.personal.mason.feop.server.blog.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;

import java.util.List;

public interface BlogSectionRepository extends JpaRepository<BlogSection, Long> {
	List<BlogSection> findByBlog(Blog blog);

	List<BlogSection> findByBlog(Blog blog, Pageable pageable);

	List<BlogSection> findByBlog(Blog blog, Sort sort);

	@Query("select count(id) from BlogSection where blog = :blog")
	long countByBlog(@Param("blog") Blog blog);
}
