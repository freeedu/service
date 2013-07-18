package org.personal.mason.feop.server.blog.repository;

import org.personal.mason.feop.server.blog.domain.BlogSection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.personal.mason.feop.server.blog.domain.Blog;
import java.util.List;

public interface BlogSectionRepository extends JpaRepository<BlogSection, Long> {
	List<BlogSection> findByBlog(Blog blog);

	List<BlogSection> findByBlog(Blog blog, Pageable pageable);

	List<BlogSection> findByBlog(Blog blog, Sort sort);

	@Query("select count(id) from BlogSection where blog = :blog")
	long countByBlog(Blog blog);
}
