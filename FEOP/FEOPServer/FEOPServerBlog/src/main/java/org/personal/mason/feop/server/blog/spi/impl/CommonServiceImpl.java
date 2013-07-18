package org.personal.mason.feop.server.blog.spi.impl;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.Blog;
import org.personal.mason.feop.server.blog.domain.BlogSection;
import org.personal.mason.feop.server.blog.domain.Common;
import org.personal.mason.feop.server.blog.repository.CommonRepository;
import org.personal.mason.feop.server.blog.spi.CommonService;
import org.personal.mason.feop.server.blog.utils.PageableUtils;
import org.personal.mason.feop.server.blog.utils.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommonServiceImpl implements CommonService {

	private CommonRepository commonRepository;

	@Autowired
	public void setCommonRepository(CommonRepository commonRepository) {
		this.commonRepository = commonRepository;
	}

	@Override
	@Transactional
	public void save(Common common) {
		commonRepository.save(common);
	}

	@Override
	@Transactional
	public Common findById(Long id) {
		return commonRepository.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Common common) {
		commonRepository.delete(common);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		commonRepository.delete(id);
	}

	@Override
	@Transactional
	public List<Common> findByBlog(Blog blog) {
		return commonRepository.findByBlog(blog);
	}

	@Override
	@Transactional
	public List<Common> findByBlog(Blog blog, int page, int size) {
		return commonRepository.findByBlog(blog, PageableUtils.getPageable(page, size, SortUtils.getSortASC("createTime")));
	}

	@Override
	@Transactional
	public List<Common> findByBlog(Blog blog, Sort sort) {
		if (sort == null) {
			sort = SortUtils.getSortASC("createTime");
		}
		return commonRepository.findByBlog(blog, sort);
	}

	@Override
	@Transactional
	public List<Common> findByBlogSection(BlogSection blogsection) {
		return commonRepository.findByBlogSection(blogsection);
	}

	@Override
	@Transactional
	public List<Common> findByBlogSection(BlogSection blogsection, int page, int size) {
		return commonRepository.findByBlogSection(blogsection, PageableUtils.getPageable(page, size, SortUtils.getSortASC("createTime")));
	}

	@Override
	@Transactional
	public List<Common> findByBlogSection(BlogSection blogsection, Sort sort) {
		if (sort == null) {
			sort = SortUtils.getSortASC("createTime");
		}
		return commonRepository.findByBlogSection(blogsection, sort);
	}

	@Override
	@Transactional
	public List<Common> findByAuthor(String author) {
		return commonRepository.findByAuthor(author);
	}

	@Override
	@Transactional
	public List<Common> findByAuthor(String author, int page, int size) {
		return commonRepository.findByAuthor(author, PageableUtils.getPageable(page, size, SortUtils.getSortASC("createTime")));
	}

	@Override
	@Transactional
	public List<Common> findByAuthor(String author, Sort sort) {
		if (sort == null) {
			sort = SortUtils.getSortASC("createTime");
		}
		return commonRepository.findByAuthor(author, sort);
	}

	@Override
	@Transactional
	public List<Common> findByCommon(Common common) {
		return commonRepository.findByCommon(common);
	}

	@Override
	@Transactional
	public List<Common> findByCommon(Common common, int page, int size) {
		return commonRepository.findByCommon(common, PageableUtils.getPageable(page, size, SortUtils.getSortASC("createTime")));
	}

	@Override
	@Transactional
	public List<Common> findByCommon(Common common, Sort sort) {
		if (sort == null) {
			sort = SortUtils.getSortASC("createTime");
		}
		return commonRepository.findByCommon(common, sort);
	}

	@Override
	public Long count(Blog blog) {
		return commonRepository.countByBlog(blog);
	}

	@Override
	public Long count(BlogSection section) {
		return commonRepository.countByBlogSection(section);
	}

	@Override
	public Long count(Common pc) {
		return commonRepository.countByCommon(pc);
	}
}
