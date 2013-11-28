package org.personal.mason.feop.server.blog.domain.service.impl;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;
import org.personal.mason.feop.server.blog.domain.model.Comment;
import org.personal.mason.feop.server.blog.domain.repository.CommentRepository;
import org.personal.mason.feop.server.blog.domain.service.CommentService;
import org.personal.mason.feop.server.blog.utils.PageableUtils;
import org.personal.mason.feop.server.blog.utils.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Page<Comment> findByBlog(Blog blog, Pageable pageable) {
        return commentRepository.findByBlog(blog, PageableUtils.getPageable(pageable, SortUtils.getSortASC("createTime")));
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
    public Page<Comment> findByBlogSection(BlogSection blogsection, Pageable pageable) {
        return commentRepository.findByBlogSection(blogsection, PageableUtils.getPageable(pageable, SortUtils.getSortASC("createTime")));
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
    public Page<Comment> findByAuthor(String author, Pageable pageable) {
        return commentRepository.findByAuthor(author, PageableUtils.getPageable(pageable, SortUtils.getSortASC("createTime")));
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
    public Page<Comment> findByComment(Comment comment, Pageable pageable) {
        return commentRepository.findByComment(comment, PageableUtils.getPageable(pageable, SortUtils.getSortASC("createTime")));
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
    public Integer count(Blog blog) {
        return commentRepository.countByBlog(blog);
    }

    @Override
    public Integer count(BlogSection section) {
        return commentRepository.countByBlogSection(section);
    }

    @Override
    public Integer count(Comment pc) {
        return commentRepository.countByComment(pc);
    }
}
