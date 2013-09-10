package org.personal.mason.feop.server.blog.domain.repository;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.model.Sery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BlogRepository extends JpaRepository<Blog, Long> {

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

	@Query("select count(id) from Blog where authorUid = :authorUid")
	Integer countByAuthorUid(@Param("authorUid") String authorUid);

	@Query("select count(id) from Blog where category = :category")
	Integer countByCategory(@Param("category") Category category);

	@Query("select count(id) from Blog where sery = :sery")
	Integer countBySery(@Param("sery") Sery sery);

	@Query("select count(id) from Blog where blogDesc like %:blogdesc%")
	Integer countByBlogDescLike(@Param("blogdesc") String blogdesc);

	@Query("select count(id) from Blog where blogTitle like %:blogtitle%")
	Integer countByBlogTitleLike(@Param("blogtitle") String blogtitle);
}
