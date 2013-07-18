package org.personal.mason.feop.server.blog.mvc.model;

import org.personal.mason.feop.server.blog.domain.Category;

public class CategoryModel {

	private Long id;
	private String categoryName;
	private String description;
	private Long parentCategoryId;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public Long getParentCategoryId() {
		return parentCategoryId;
	}

	public static Category convert(CategoryModel categoryModel) {
		Category category = new Category();
		category.setId(categoryModel.getId());
		category.setCategoryName(categoryModel.getCategoryName());
		category.setDescription(categoryModel.getDescription());

		return category;
	}

	public static CategoryModel revert(Category category) {
		CategoryModel model = new CategoryModel();
		model.setId(category.getId());
		model.setCategoryName(category.getCategoryName());
		model.setDescription(category.getDescription());
		model.setParentCategoryId(category.getCategory().getId());
		return model;
	}
}
