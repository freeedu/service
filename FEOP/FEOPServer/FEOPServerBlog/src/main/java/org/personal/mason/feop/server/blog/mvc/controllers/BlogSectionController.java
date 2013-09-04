package org.personal.mason.feop.server.blog.mvc.controllers;

import java.security.Principal;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;
import org.personal.mason.feop.server.blog.domain.service.BlogSectionService;
import org.personal.mason.feop.server.blog.domain.service.BlogService;
import org.personal.mason.feop.server.blog.mvc.model.BlogSectionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogSectionController {

	private BlogSectionService blogSectionService;
	private BlogService blogService;

	@Autowired
	public void setBlogSectionService(BlogSectionService blogSectionService) {
		this.blogSectionService = blogSectionService;
	}

	@Autowired
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	@RequestMapping(value = "/section/create", method = RequestMethod.GET)
	public String createSection(@RequestParam("id") Long blogId, Model model) {
		BlogSectionModel blogSectionModel = new BlogSectionModel();
		blogSectionModel.setBlogId(blogId);
		model.addAttribute("/section", blogSectionModel);
		return "/section/form";
	}

	@RequestMapping(value = "/section/save", method = RequestMethod.POST)
	public String createSection(@Validated BlogSectionModel section, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}

		BlogSection blogSection = BlogSectionModel.convert(section);
		Blog blog = blogService.findById(section.getBlogId());
		blogSectionService.save(blog, blogSection);

		return "";
	}

	@RequestMapping(value = "/section/update", method = RequestMethod.GET)
	public String updateSection(@RequestParam("id") Long id, Model model) {
		BlogSection section = blogSectionService.findById(id);
		BlogSectionModel blogSectionModel = BlogSectionModel.revert(section);
		model.addAttribute("section", blogSectionModel);
		return "";
	}

	@RequestMapping(value = "/section/update", method = RequestMethod.PUT)
	public String updateBlog(@Validated BlogSectionModel sectionModel, BindingResult result, Principal principal, Model model) {
		if (principal == null || principal.getName().isEmpty()) {
			return "redirect:/user/login";
		}

		if (result.hasErrors()) {
			return null;
		}

		BlogSection section = BlogSectionModel.convert(sectionModel);
		BlogSection updatedBlogSection = blogSectionService.update(section);

		BlogSectionModel updatedBlogSectionModel = BlogSectionModel.revert(updatedBlogSection);
		model.addAttribute("section", updatedBlogSectionModel);
		return "section/view";
	}

	@RequestMapping(value = "/section/delete", method = RequestMethod.DELETE)
	public void deleteSection(@RequestParam("id") Long id) {
		blogSectionService.delete(id);
	}
}
