package org.personal.mason.feop.server.blog.spi.impl;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.Category;
import org.personal.mason.feop.server.blog.repository.CategoryRepository;
import org.personal.mason.feop.server.blog.spi.CategoryService;
import org.personal.mason.feop.server.blog.utils.PageableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	@Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	@Transactional
	public void save(Category category) {
		categoryRepository.save(category);
	}

	@Override
	@Transactional
	public Category update(Category category) {
		return categoryRepository.saveAndFlush(category);
	}

	@Override
	@Transactional
	public List<Category> findByCategory(Category category) {
		return categoryRepository.findByCategory(category);
	}

	@Override
	@Transactional
	public List<Category> findByCategoryIsNull() {
		return categoryRepository.findByCategoryIsNull();
	}

	@Override
	@Transactional
	public List<Category> findByCategory(Category category, int page, int size) {
		return categoryRepository.findByCategory(category, PageableUtils.getPageable(page, size));
	}

	@Override
	@Transactional
	public List<Category> findByCategoryIsNull(int page, int size) {
		return categoryRepository.findByCategoryIsNull(PageableUtils.getPageable(page, size));
	}

	@Override
	@Transactional
	public void delete(Category category) {
		categoryRepository.delete(category);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		categoryRepository.delete(id);
	}

	@Override
	public Category findById(Long id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public Category findByCategoryName(String categoryName) {
		List<Category> cats = categoryRepository.findByCategoryName(categoryName);
		return cats.size() > 0 ? cats.get(0) : null;
	}
}
