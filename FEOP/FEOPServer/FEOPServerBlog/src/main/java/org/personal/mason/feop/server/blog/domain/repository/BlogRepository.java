package org.personal.mason.feop.server.blog.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.String;
import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.model.Sery;

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
	long countByAuthorUid(@Param("authorUid") String authorUid);

	@Query("select count(id) from Blog where category = :category")
	long countByCategory(@Param("category") Category category);

	@Query("select count(id) from Blog where sery = :sery")
	long countBySery(@Param("sery") Sery sery);

	@Query("select count(id) from Blog where blogDesc like %:blogdesc%")
	long countByBlogDescLike(@Param("blogdesc") String blogdesc);

	@Query("select count(id) from Blog where blogTitle like %:blogtitle%")
	long countByBlogTitleLike(@Param("blogtitle") String blogtitle);
}
