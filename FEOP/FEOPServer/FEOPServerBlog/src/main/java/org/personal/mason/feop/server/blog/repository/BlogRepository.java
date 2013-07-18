package org.personal.mason.feop.server.blog.repository;

import org.personal.mason.feop.server.blog.domain.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.String;
import java.util.List;
import org.personal.mason.feop.server.blog.domain.Category;
import org.personal.mason.feop.server.blog.domain.Sery;

public interface BlogRepository extends JpaRepository<Blog, Long> {

	List<Blog> findByAuthorUid(String authoruid);

	List<Blog> findByCategory(Category category);

	List<Blog> findByBlogDescLike(String blogdesc);

	List<Blog> findByBlogTitleLike(String blogtitle);

	List<Blog> findBySery(Sery sery);

	List<Blog> findByAuthorUid(String authoruid, Pageable pageable);

	List<Blog> findByCategory(Category category, Pageable pageable);

	List<Blog> findByBlogDescLike(String blogdesc, Pageable pageable);

	List<Blog> findByBlogTitleLike(String blogtitle, Pageable pageable);

	List<Blog> findBySery(Sery sery, Pageable pageable);

	@Query("select count(id) from Blog where authorUid = :authorUid")
	long countByAuthorUid(String authorUid);

	@Query("select count(id) from Blog where category = :category")
	long countByCategory(Category category);

	@Query("select count(id) from Blog where sery = :sery")
	long countBySery(Sery sery);

	@Query("select count(id) from Blog where blogDesc like %:blogdesc%")
	long countByBlogDescLike(String blogdesc);

	@Query("select count(id) from Blog where blogTitle like %:blogtitle%")
	long countByBlogTitleLike(String blogtitle);
}
