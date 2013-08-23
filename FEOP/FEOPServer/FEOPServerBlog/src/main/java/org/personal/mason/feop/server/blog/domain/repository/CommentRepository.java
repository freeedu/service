package org.personal.mason.feop.server.blog.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;
import org.personal.mason.feop.server.blog.domain.model.Comment;

import java.lang.String;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByBlog(Blog blog);

	List<Comment> findByBlog(Blog blog, Pageable pageable);

	List<Comment> findByBlog(Blog blog, Sort sort);

	List<Comment> findByBlogSection(BlogSection blogsection);

	List<Comment> findByBlogSection(BlogSection blogsection, Pageable pageable);

	List<Comment> findByBlogSection(BlogSection blogsection, Sort sort);

	List<Comment> findByAuthor(String author);

	List<Comment> findByAuthor(String author, Pageable pageable);

	List<Comment> findByAuthor(String author, Sort sort);

	List<Comment> findByComment(Comment comment);

	List<Comment> findByComment(Comment comment, Pageable pageable);

	List<Comment> findByComment(Comment comment, Sort sort);

	@Query("select count(id) from Comment where blog=:blog")
	long countByBlog(Blog blog);

	@Query("select count(id) from Comment where common=:comment")
	long countByComment(Comment comment);

	@Query("select count(id) from Comment where blogSection=:section")
	long countByBlogSection(BlogSection section);

}
