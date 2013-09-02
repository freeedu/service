package org.personal.mason.feop.server.blog.domain.service;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogSectionService {
	List<BlogSection> findByBlog(Blog blog);

	Page<BlogSection> findByBlog(Blog blog, Pageable pageable);

	void save(Blog blog, BlogSection section);

	BlogSection update(BlogSection section);

	void delete(BlogSection section);

	void delete(Long id);

	BlogSection findById(Long id);

	Long count(Blog blog);

	Long getMaxSequence(Blog blog);
}
