package org.personal.mason.feop.server.blog.domain.repository;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogSectionRepository extends JpaRepository<BlogSection, Long> {
    List<BlogSection> findByBlog(Blog blog);

    Page<BlogSection> findByBlog(Blog blog, Pageable pageable);

    List<BlogSection> findByBlog(Blog blog, Sort sort);

    @Query("select count(id) from BlogSection where blog = :blog")
    Integer countByBlog(@Param("blog") Blog blog);

    @Query("select max(sequence) from BlogSection where blog = :blog")
    Integer getMaxSequenceOfBlog(@Param("blog") Blog blog);
}
