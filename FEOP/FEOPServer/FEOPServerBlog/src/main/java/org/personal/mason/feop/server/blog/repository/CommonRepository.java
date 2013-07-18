package org.personal.mason.feop.server.blog.repository;

import org.personal.mason.feop.server.blog.domain.Common;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.personal.mason.feop.server.blog.domain.Blog;
import java.util.List;
import org.personal.mason.feop.server.blog.domain.BlogSection;
import java.lang.String;

public interface CommonRepository extends JpaRepository<Common, Long> {

	List<Common> findByBlog(Blog blog);

	List<Common> findByBlog(Blog blog, Pageable pageable);

	List<Common> findByBlog(Blog blog, Pageable pageable, Sort sort);

	List<Common> findByBlog(Blog blog, Sort sort);

	List<Common> findByBlogSection(BlogSection blogsection);

	List<Common> findByBlogSection(BlogSection blogsection, Pageable pageable);

	List<Common> findByBlogSection(BlogSection blogsection, Sort sort);

	List<Common> findByBlogSection(BlogSection blogsection, Pageable pageable, Sort sort);

	List<Common> findByAuthor(String author);

	List<Common> findByAuthor(String author, Pageable pageable);

	List<Common> findByAuthor(String author, Sort sort);

	List<Common> findByAuthor(String author, Pageable pageable, Sort sort);

	List<Common> findByCommon(Common common);

	List<Common> findByCommon(Common common, Pageable pageable);

	List<Common> findByCommon(Common common, Sort sort);

	List<Common> findByCommon(Common common, Pageable pageable, Sort sort);

	@Query("select count(id) from Common where blog=:blog")
	long countByBlog(Blog blog);

	@Query("select count(id) from Common where common=:common")
	long countByCommon(Common common);

	@Query("select count(id) from Common where blogSection:=section")
	long countByBlogSection(BlogSection section);

}
