package org.personal.mason.feop.server.blog.mvc.controllers;

import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.service.CategoryService;
import org.personal.mason.feop.server.blog.mvc.model.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/cat/form", method = RequestMethod.GET)
    public String createCategory(Model model) {
        model.addAttribute("cat", new CategoryModel());
        return "";
    }

    @RequestMapping(value = "/cat/save", method = RequestMethod.POST)
    public String saveCategory(@Validated CategoryModel categoryModel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return null;
        }

        if (categoryService.findByCategoryName(categoryModel.getCategoryName()) != null) {
            result.rejectValue("categoryName", "error.category.categoryname", "category with this name already exist");
            return null;
        }

        Category category = CategoryModel.convert(categoryModel);
        if (categoryModel.getParentCategoryId() != null) {
            Category pcat = categoryService.findById(categoryModel.getParentCategoryId());
            category.setCategory(pcat);
        }

        categoryService.save(category);

        return "";
    }

    @RequestMapping(value = "/cat/delete", method = RequestMethod.GET)
    public void deleteCategory(@RequestParam("id") Long id) {
        categoryService.delete(id);
    }
}
