package org.personal.mason.feop.server.blog.mvc.rest;

import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.service.CategoryService;
import org.personal.mason.feop.server.blog.mvc.model.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("api")
public class CategoryApi {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * @param categoryModel
     * @return
     */
    @RequestMapping(value = "/cat/save", method = RequestMethod.POST)
    @ResponseBody
    public CategoryModel saveCategory(CategoryModel categoryModel) {
        Category category = CategoryModel.convert(categoryModel);
        if (categoryModel.getParentCategoryId() != null) {
            Category pcat = categoryService.findById(categoryModel.getParentCategoryId());
            category.setCategory(pcat);
        }

        categoryService.save(category);

        return CategoryModel.revert(category);
    }

    /**
     * @param id
     */
    @RequestMapping(value = "/cat/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteCategory(@RequestParam("id") Long id) {
        categoryService.delete(id);
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/cat", method = RequestMethod.GET)
    @ResponseBody
    public CategoryModel findCategoryById(@RequestParam("id") Long id) {
        Category category = categoryService.findById(id);
        return CategoryModel.revert(category);
    }

    /**
     * @return
     */
    @RequestMapping(value = "/cat/top", method = RequestMethod.GET)
    @ResponseBody
    public List<CategoryModel> findTopLevelCategories() {
        List<Category> topLevelCategories = categoryService.findByCategoryIsNull();
        List<CategoryModel> models = new ArrayList<>();

        if (topLevelCategories != null) {
            for (Category cat : topLevelCategories) {
                CategoryModel model = CategoryModel.revert(cat);
                models.add(model);
            }
        }

        return models;
    }

    /**
     * @param cid
     * @return
     */
    @RequestMapping(value = "/cat/sub", method = RequestMethod.GET)
    @ResponseBody
    public List<CategoryModel> findSubCategories(@RequestParam("cid") Long cid) {
        Category category = categoryService.findById(cid);

        List<Category> categories = categoryService.findByCategory(category);
        List<CategoryModel> models = new ArrayList<>();

        if (categories != null) {
            for (Category cat : categories) {
                CategoryModel model = CategoryModel.revert(cat);
                models.add(model);
            }
        }

        return models;
    }
}
