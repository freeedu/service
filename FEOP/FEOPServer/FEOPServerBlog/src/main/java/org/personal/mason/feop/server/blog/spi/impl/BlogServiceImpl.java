package org.personal.mason.feop.server.blog.spi.impl;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.Blog;
import org.personal.mason.feop.server.blog.domain.Category;
import org.personal.mason.feop.server.blog.domain.Sery;
import org.personal.mason.feop.server.blog.repository.BlogRepository;
import org.personal.mason.feop.server.blog.spi.BlogService;
import org.personal.mason.feop.server.blog.utils.PageableUtils;
import org.personal.mason.feop.server.blog.utils.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogServiceImpl implements BlogService {

	private BlogRepository blogRepository;

	@Autowired
	public void setBlogRepository(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}

	@Override
	@Transactional
	public List<Blog> findAll(int page, int size) {
		return blogRepository.findAll(
				PageableUtils.getPageable(page, size, SortUtils.appendSortDESC(SortUtils.getSortDESC("lastUpdate"), "createDate"))).getContent();
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
	public List<Blog> findByAuthorUid(String authoruid, int page, int size) {
		return blogRepository.findByAuthorUid(authoruid, PageableUtils.getPageable(page, size, SortUtils.getSortDESC("lastUpdate")));
	}

	@Override
	@Transactional
	public List<Blog> findByCategory(Category category, int page, int size) {
		return blogRepository.findByCategory(category, PageableUtils.getPageable(page, size, SortUtils.getSortDESC("lastUpdate")));
	}

	@Override
	@Transactional
	public List<Blog> findByBlogDescLike(String blogdesc, int page, int size) {
		return blogRepository.findByBlogDescLike(blogdesc, PageableUtils.getPageable(page, size, SortUtils.getSortDESC("lastUpdate")));
	}

	@Override
	@Transactional
	public List<Blog> findByBlogTitleLike(String blogtitle, int page, int size) {
		return blogRepository.findByBlogTitleLike(blogtitle, PageableUtils.getPageable(page, size, SortUtils.getSortDESC("lastUpdate")));
	}

	@Override
	@Transactional
	public List<Blog> findBySery(Sery sery, int page, int size) {
		return blogRepository.findBySery(sery, PageableUtils.getPageable(page, size, SortUtils.getSortDESC("lastUpdate")));
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
	public Long getCount(String uid) {
		return blogRepository.countByAuthorUid(uid);
	}

	@Override
	public Long getCount(Category cat) {
		return blogRepository.countByCategory(cat);
	}

	@Override
	public Long getCount(Sery sery) {
		return blogRepository.countBySery(sery);
	}

}
