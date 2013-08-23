package org.personal.mason.feop.server.blog.domain.service;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;

public interface BlogSectionService {
	List<BlogSection> findByBlog(Blog blog);

	List<BlogSection> findByBlog(Blog blog, int page, int size);

	void save(Blog blog, BlogSection section);

	BlogSection update(BlogSection section);

	void delete(BlogSection section);

	void delete(Long id);

	BlogSection findById(Long id);

	Long count(Blog blog);
}
