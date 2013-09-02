package org.personal.mason.feop.server.blog.domain.service;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;
import org.personal.mason.feop.server.blog.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface CommentService {

	void save(Comment comment);

	Comment findById(Long id);

	void delete(Comment comment);

	void delete(Long id);

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

	Long count(Blog blog);

	Long count(BlogSection section);

	Long count(Comment pc);

}
