package org.personal.mason.feop.server.blog.domain.service;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogSectionService {
    List<BlogSection> findByBlog(Blog blog);

    Page<BlogSection> findByBlog(Blog blog, Pageable pageable);

    void save(Blog blog, BlogSection section);

    BlogSection update(BlogSection section);

    void delete(BlogSection section);

    void delete(Long id);

    BlogSection findById(Long id);

    Integer count(Blog blog);

    Integer getMaxSequence(Blog blog);
}
