package org.personal.mason.feop.server.blog.domain.service;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.model.Sery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {

	Page<Blog> findAll(Pageable pageable);

	long getCount();

	List<Blog> findByAuthorUid(String authoruid);

	List<Blog> findByCategory(Category category);

	List<Blog> findByBlogDescLike(String blogdesc);

	List<Blog> findByBlogTitleLike(String blogtitle);

	List<Blog> findBySery(Sery sery);

	Page<Blog> findByAuthorUid(String authoruid, Pageable pageable);

	Page<Blog> findByCategory(Category category, Pageable pageable);

	Page<Blog> findByBlogDescLike(String blogdesc, Pageable pageable);

	Page<Blog> findByBlogTitleLike(String blogtitle, Pageable pageable);

	Page<Blog> findBySery(Sery sery, Pageable pageable);

	Blog update(Blog blog);

	void save(Blog blog);

	void delete(Long id);

	void delete(Blog blog);

	Blog findById(Long id);

	Integer getCount(String uid);

	Integer getCount(Category cat);

	Integer getCount(Sery sery);
}
