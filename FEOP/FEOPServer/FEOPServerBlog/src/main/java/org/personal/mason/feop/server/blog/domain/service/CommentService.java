package org.personal.mason.feop.server.blog.domain.service;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;
import org.personal.mason.feop.server.blog.domain.model.Comment;
import org.springframework.data.domain.Sort;

public interface CommentService {

	void save(Comment comment);

	Comment findById(Long id);

	void delete(Comment comment);

	void delete(Long id);

	List<Comment> findByBlog(Blog blog);

	List<Comment> findByBlog(Blog blog, int page, int size);

	List<Comment> findByBlog(Blog blog, Sort sort);

	List<Comment> findByBlogSection(BlogSection blogsection);

	List<Comment> findByBlogSection(BlogSection blogsection, int page, int size);

	List<Comment> findByBlogSection(BlogSection blogsection, Sort sort);

	List<Comment> findByAuthor(String author);

	List<Comment> findByAuthor(String author, int page, int size);

	List<Comment> findByAuthor(String author, Sort sort);

	List<Comment> findByComment(Comment comment);

	List<Comment> findByComment(Comment comment, int page, int size);

	List<Comment> findByComment(Comment comment, Sort sort);

	Long count(Blog blog);

	Long count(BlogSection section);

	Long count(Comment pc);

}
