package org.personal.mason.feop.server.blog.domain.service.impl;

import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.repository.CategoryRepository;
import org.personal.mason.feop.server.blog.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Page<Category> findByCategory(Category category, Pageable pageable) {
        return categoryRepository.findByCategory(category, pageable);
    }

    @Override
    @Transactional
    public Page<Category> findByCategoryIsNull(Pageable pageable) {
        return categoryRepository.findByCategoryIsNull(pageable);
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
