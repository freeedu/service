package org.personal.mason.feop.server.blog.domain.service.impl;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;
import org.personal.mason.feop.server.blog.domain.model.Comment;
import org.personal.mason.feop.server.blog.domain.repository.CommentRepository;
import org.personal.mason.feop.server.blog.domain.service.CommentService;
import org.personal.mason.feop.server.blog.utils.PageableUtils;
import org.personal.mason.feop.server.blog.utils.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;

	@Autowired
	public void setCommonRepository(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	@Override
	@Transactional
	public void save(Comment comment) {
		commentRepository.save(comment);
	}

	@Override
	@Transactional
	public Comment findById(Long id) {
		return commentRepository.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Comment comment) {
		commentRepository.delete(comment);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		commentRepository.delete(id);
	}

	@Override
	@Transactional
	public List<Comment> findByBlog(Blog blog) {
		return commentRepository.findByBlog(blog);
	}

	@Override
	@Transactional
	public List<Comment> findByBlog(Blog blog, int page, int size) {
		return commentRepository.findByBlog(blog, PageableUtils.getPageable(page, size, SortUtils.getSortASC("createTime")));
	}

	@Override
	@Transactional
	public List<Comment> findByBlog(Blog blog, Sort sort) {
		if (sort == null) {
			sort = SortUtils.getSortASC("createTime");
		}
		return commentRepository.findByBlog(blog, sort);
	}

	@Override
	@Transactional
	public List<Comment> findByBlogSection(BlogSection blogsection) {
		return commentRepository.findByBlogSection(blogsection);
	}

	@Override
	@Transactional
	public List<Comment> findByBlogSection(BlogSection blogsection, int page, int size) {
		return commentRepository.findByBlogSection(blogsection, PageableUtils.getPageable(page, size, SortUtils.getSortASC("createTime")));
	}

	@Override
	@Transactional
	public List<Comment> findByBlogSection(BlogSection blogsection, Sort sort) {
		if (sort == null) {
			sort = SortUtils.getSortASC("createTime");
		}
		return commentRepository.findByBlogSection(blogsection, sort);
	}

	@Override
	@Transactional
	public List<Comment> findByAuthor(String author) {
		return commentRepository.findByAuthor(author);
	}

	@Override
	@Transactional
	public List<Comment> findByAuthor(String author, int page, int size) {
		return commentRepository.findByAuthor(author, PageableUtils.getPageable(page, size, SortUtils.getSortASC("createTime")));
	}

	@Override
	@Transactional
	public List<Comment> findByAuthor(String author, Sort sort) {
		if (sort == null) {
			sort = SortUtils.getSortASC("createTime");
		}
		return commentRepository.findByAuthor(author, sort);
	}

	@Override
	@Transactional
	public List<Comment> findByComment(Comment comment) {
		return commentRepository.findByComment(comment);
	}

	@Override
	@Transactional
	public List<Comment> findByComment(Comment comment, int page, int size) {
		return commentRepository.findByComment(comment, PageableUtils.getPageable(page, size, SortUtils.getSortASC("createTime")));
	}

	@Override
	@Transactional
	public List<Comment> findByComment(Comment comment, Sort sort) {
		if (sort == null) {
			sort = SortUtils.getSortASC("createTime");
		}
		return commentRepository.findByComment(comment, sort);
	}

	@Override
	public Long count(Blog blog) {
		return commentRepository.countByBlog(blog);
	}

	@Override
	public Long count(BlogSection section) {
		return commentRepository.countByBlogSection(section);
	}

	@Override
	public Long count(Comment pc) {
		return commentRepository.countByComment(pc);
	}
}
