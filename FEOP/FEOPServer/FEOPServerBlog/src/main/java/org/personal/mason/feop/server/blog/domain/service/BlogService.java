package org.personal.mason.feop.server.blog.domain.service;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.model.Sery;

public interface BlogService {
	
	List<Blog> findAll(int page, int size);
	
	long getCount() ;

	List<Blog> findByAuthorUid(String authoruid);

	List<Blog> findByCategory(Category category);

	List<Blog> findByBlogDescLike(String blogdesc);

	List<Blog> findByBlogTitleLike(String blogtitle);

	List<Blog> findBySery(Sery sery);

	List<Blog> findByAuthorUid(String authoruid, int page, int size);

	List<Blog> findByCategory(Category category, int page, int size);

	List<Blog> findByBlogDescLike(String blogdesc, int page, int size);

	List<Blog> findByBlogTitleLike(String blogtitle, int page, int size);

	List<Blog> findBySery(Sery sery, int page, int size);

	Blog update(Blog blog);

	void save(Blog blog);

	void delete(Long id);

	void delete(Blog blog);

	Blog findById(Long id);

	Long getCount(String uid);

	Long getCount(Category cat);

	Long getCount(Sery sery);
}
