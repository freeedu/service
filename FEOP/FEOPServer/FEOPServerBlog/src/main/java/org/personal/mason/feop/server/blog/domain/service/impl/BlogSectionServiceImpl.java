package org.personal.mason.feop.server.blog.domain.service.impl;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;
import org.personal.mason.feop.server.blog.domain.repository.BlogSectionRepository;
import org.personal.mason.feop.server.blog.domain.service.BlogSectionService;
import org.personal.mason.feop.server.blog.utils.PageableUtils;
import org.personal.mason.feop.server.blog.utils.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogSectionServiceImpl implements BlogSectionService {

	private BlogSectionRepository blogSectionRepository;

	@Autowired
	public void setBlogSectionRepository(BlogSectionRepository blogSectionRepository) {
		this.blogSectionRepository = blogSectionRepository;
	}

	@Override
	@Transactional
	public List<BlogSection> findByBlog(Blog blog) {
		return blogSectionRepository.findByBlog(blog, SortUtils.getSortASC("sequence"));
	}

	@Override
	@Transactional
	public Page<BlogSection> findByBlog(Blog blog, Pageable pageable) {
		return blogSectionRepository.findByBlog(blog, PageableUtils.getPageable(pageable, SortUtils.getSortASC("sequence")));
	}

	@Override
	@Transactional
	public void save(Blog blog, BlogSection section) {
		section.setBlog(blog);
		blogSectionRepository.save(section);
	}

	@Override
	@Transactional
	public BlogSection update(BlogSection section) {
		return blogSectionRepository.saveAndFlush(section);
	}

	@Override
	@Transactional
	public void delete(BlogSection section) {
		blogSectionRepository.delete(section);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		blogSectionRepository.delete(id);
	}

	@Override
	@Transactional
	public BlogSection findById(Long id) {
		return blogSectionRepository.findOne(id);
	}

	@Override
	public Long count(Blog blog) {
		return blogSectionRepository.countByBlog(blog);
	}

	@Override
	public Long getMaxSequence(Blog blog) {
		return blogSectionRepository.getMaxSequenceOfBlog(blog);
	}

}
