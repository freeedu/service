package org.personal.mason.feop.server.blog.domain.repository;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;
import org.personal.mason.feop.server.blog.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByBlog(Blog blog);

	Page<Comment> findByBlog(Blog blog, Pageable pageable);

	List<Comment> findByBlog(Blog blog, Sort sort);

	List<Comment> findByBlogSection(BlogSection blogsection);

	Page<Comment> findByBlogSection(BlogSection blogsection, Pageable pageable);

	List<Comment> findByBlogSection(BlogSection blogsection, Sort sort);

	List<Comment> findByAuthor(String author);

	Page<Comment> findByAuthor(String author, Pageable pageable);

	List<Comment> findByAuthor(String author, Sort sort);

	List<Comment> findByComment(Comment comment);

	Page<Comment> findByComment(Comment comment, Pageable pageable);

	List<Comment> findByComment(Comment comment, Sort sort);

	@Query("select count(id) from Comment where blog=:blog")
	Integer countByBlog(@Param("blog") Blog blog);

	@Query("select count(id) from Comment where common=:comment")
	Integer countByComment(@Param("comment") Comment comment);

	@Query("select count(id) from Comment where blogSection=:section")
	Integer countByBlogSection(@Param("section") BlogSection section);

}
