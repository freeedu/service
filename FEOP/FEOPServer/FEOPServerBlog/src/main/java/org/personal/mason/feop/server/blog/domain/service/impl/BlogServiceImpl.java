package org.personal.mason.feop.server.blog.domain.service.impl;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.model.Sery;
import org.personal.mason.feop.server.blog.domain.repository.BlogRepository;
import org.personal.mason.feop.server.blog.domain.service.BlogService;
import org.personal.mason.feop.server.blog.utils.PageableUtils;
import org.personal.mason.feop.server.blog.utils.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private BlogRepository blogRepository;

    @Autowired
    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    @Transactional
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository
                .findAll(PageableUtils.getPageable(pageable, SortUtils.appendSortDESC(SortUtils.getSortDESC("lastUpdate"), "createDate")));
    }

    @Override
    @Transactional
    public long getCount() {
        return blogRepository.count();
    }

    @Override
    @Transactional
    public List<Blog> findByAuthorUid(String authoruid) {
        return blogRepository.findByAuthorUid(authoruid);
    }

    @Override
    @Transactional
    public List<Blog> findByCategory(Category category) {
        return blogRepository.findByCategory(category);
    }

    @Override
    @Transactional
    public List<Blog> findByBlogDescLike(String blogdesc) {
        return blogRepository.findByBlogDescLike(blogdesc);
    }

    @Override
    @Transactional
    public List<Blog> findByBlogTitleLike(String blogtitle) {
        return blogRepository.findByBlogTitleLike(blogtitle);
    }

    @Override
    @Transactional
    public List<Blog> findBySery(Sery sery) {
        return blogRepository.findBySery(sery);
    }

    @Override
    @Transactional
    public Page<Blog> findByAuthorUid(String authoruid, Pageable pageable) {
        return blogRepository.findByAuthorUid(authoruid, PageableUtils.getPageable(pageable, SortUtils.getSortDESC("lastUpdate")));
    }

    @Override
    @Transactional
    public Page<Blog> findByCategory(Category category, Pageable pageable) {
        return blogRepository.findByCategory(category, PageableUtils.getPageable(pageable, SortUtils.getSortDESC("lastUpdate")));
    }

    @Override
    @Transactional
    public Page<Blog> findByBlogDescLike(String blogdesc, Pageable pageable) {
        return blogRepository.findByBlogDescLike(blogdesc, PageableUtils.getPageable(pageable, SortUtils.getSortDESC("lastUpdate")));
    }

    @Override
    @Transactional
    public Page<Blog> findByBlogTitleLike(String blogtitle, Pageable pageable) {
        return blogRepository.findByBlogTitleLike(blogtitle, PageableUtils.getPageable(pageable, SortUtils.getSortDESC("lastUpdate")));
    }

    @Override
    @Transactional
    public Page<Blog> findBySery(Sery sery, Pageable pageable) {
        return blogRepository.findBySery(sery, PageableUtils.getPageable(pageable, SortUtils.getSortDESC("lastUpdate")));
    }

    @Override
    @Transactional
    public Blog update(Blog blog) {
        return blogRepository.saveAndFlush(blog);
    }

    @Override
    @Transactional
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        blogRepository.delete(id);
    }

    @Override
    @Transactional
    public void delete(Blog blog) {
        blogRepository.delete(blog);
    }

    @Override
    @Transactional
    public Blog findById(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public Integer getCount(String uid) {
        return blogRepository.countByAuthorUid(uid);
    }

    @Override
    public Integer getCount(Category cat) {
        return blogRepository.countByCategory(cat);
    }

    @Override
    public Integer getCount(Sery sery) {
        return blogRepository.countBySery(sery);
    }

}
