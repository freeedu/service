package org.personal.mason.feop.server.blog.mvc.controllers;

import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.model.Sery;
import org.personal.mason.feop.server.blog.domain.service.CategoryService;
import org.personal.mason.feop.server.blog.domain.service.SeryService;
import org.personal.mason.feop.server.blog.mvc.model.SeryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SeryController {

    private SeryService seryService;
    private CategoryService categoryService;

    @Autowired
    public void setSeryService(SeryService seryService) {
        this.seryService = seryService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/sery/create", method = RequestMethod.GET)
    public String createSery(Model model) {
        model.addAttribute("seryModel", new SeryModel());
        return "";
    }

    @RequestMapping(value = "/sery/save", method = RequestMethod.POST)
    public String saveSery(@Validated SeryModel seryModel, BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }

        Sery sery = SeryModel.convert(seryModel);
        Category category = categoryService.findById(seryModel.getCategoryId());
        sery.setCategory(category);

        seryService.save(sery);
        return "";
    }

    @RequestMapping(value = "/sery/delete", method = RequestMethod.GET)
    public void deleteSery(@RequestParam("id") Long id) {
        seryService.delete(id);
    }

    @RequestMapping(value = "/sery/list", method = RequestMethod.GET)
    public String findByName(@RequestParam("q") String query, Pageable pageable, Model model) {
        Page<Sery> series = seryService.findByName(query, pageable);
        model.addAttribute("series", series);
        return "";
    }

    @RequestMapping(value = "/sery/c/list", method = RequestMethod.GET)
    public String findByCategory(@RequestParam("c") Long categoryId, Pageable pageable, Model model) {
        Category cat = categoryService.findById(categoryId);
        Page<Sery> series = seryService.findByCategory(cat, pageable);
        model.addAttribute("series", series);
        return "";
    }
}
