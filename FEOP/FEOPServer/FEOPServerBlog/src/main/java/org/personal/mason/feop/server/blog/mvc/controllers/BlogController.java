package org.personal.mason.feop.server.blog.mvc.controllers;

import java.security.Principal;
import java.util.List;

import org.personal.mason.feop.server.blog.domain.Blog;
import org.personal.mason.feop.server.blog.domain.Category;
import org.personal.mason.feop.server.blog.domain.Sery;
import org.personal.mason.feop.server.blog.mvc.model.BlogModel;
import org.personal.mason.feop.server.blog.mvc.utils.PrincipalUtils;
import org.personal.mason.feop.server.blog.spi.BlogService;
import org.personal.mason.feop.server.blog.spi.CategoryService;
import org.personal.mason.feop.server.blog.spi.SeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {

	private BlogService blogService;
	private CategoryService categoryService;
	private SeryService seryService;

	@Autowired
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Autowired
	public void setSeryService(SeryService seryService) {
		this.seryService = seryService;
	}

	@RequestMapping(value = "blog/create", method = RequestMethod.GET)
	public String createBlog(@ModelAttribute BlogModel blogModel) {
		return "blog/form";
	}

	@RequestMapping(value = "blog/create", method = RequestMethod.POST)
	public String saveBlog(@Validated BlogModel blogModel, BindingResult result, Principal principal) {
		if (principal == null || principal.getName().isEmpty()) {
			return "redirect:/login";
		}

		if (result.hasErrors()) {
			return null;
		}

		Blog blog = BlogModel.convert(blogModel);
		blog.setAuthorName(PrincipalUtils.getUserName(principal));
		blog.setAuthorUid(PrincipalUtils.getUid(principal));
		blogService.save(blog);
		return "blog/form";
	}

	@RequestMapping(value = "blog/update", method = RequestMethod.GET)
	public String updateBlog(@RequestParam("id") Long id, Model model) {
		Blog blog = blogService.findById(id);

		BlogModel blogModel = BlogModel.revert(blog);
		model.addAttribute("blogModel", blogModel);
		return "blog/update";
	}

	@RequestMapping(value = "blog/update", method = RequestMethod.PUT)
	public String updateBlog(@Validated BlogModel blogModel, BindingResult result, Principal principal, Model model) {
		if (principal == null || principal.getName().isEmpty()) {
			return "redirect:/login";
		}

		if (result.hasErrors()) {
			return null;
		}

		Blog blog = BlogModel.convert(blogModel);
		blog.setAuthorName(PrincipalUtils.getUserName(principal));
		blog.setAuthorUid(PrincipalUtils.getUid(principal));
		Blog updatedBlog = blogService.update(blog);
		BlogModel updatedBlogModel = BlogModel.revert(updatedBlog);
		model.addAttribute("blog", updatedBlogModel);
		return "blog/view";
	}

	@RequestMapping(value = "blog/delete", method = RequestMethod.DELETE)
	public void deleteBlog(@RequestParam("id") Long id) {
		blogService.delete(id);
	}

	@RequestMapping(value = "blog/newest")
	public String getNewestBlogs(@RequestParam("p") Integer page, @RequestParam("s") Integer size, Model model) {
		if (page == null || page < 0) {
			page = 0;
		}

		if (size == null || size < 0) {
			size = 10;
		}

		List<Blog> blogs = blogService.findAll(page, size);
		Long bcounts = blogService.getCount();
		model.addAttribute("blogs", blogs);
		model.addAttribute("count", bcounts);
		return "index";
	}

	@RequestMapping(value = "blog/list", method = RequestMethod.GET)
	public String findMyBlog(Principal principal, @RequestParam("p") Integer page, @RequestParam("s") Integer size, Model model) {
		String uid = PrincipalUtils.getUid(principal);
		List<Blog> blogs = blogService.findByAuthorUid(uid, page, size);
		Long bcounts = blogService.getCount(uid);
		model.addAttribute("blogs", blogs);
		model.addAttribute("count", bcounts);
		model.addAttribute("cpage", page);
		return "";
	}

	@RequestMapping(value = "blog/list/cat", method = RequestMethod.GET)
	public String findCategoryBlog(@RequestParam("c") Long categoryId, @RequestParam("p") Integer page, @RequestParam("s") Integer size, Model model) {
		Category cat = categoryService.findById(categoryId);
		List<Blog> blogs = blogService.findByCategory(cat, page, size);
		Long bcounts = blogService.getCount(cat);
		model.addAttribute("blogs", blogs);
		model.addAttribute("count", bcounts);
		model.addAttribute("cpage", page);
		return "";
	}

	@RequestMapping(value = "blog/list/sery", method = RequestMethod.GET)
	public String findSeryBlog(@RequestParam("s") Long seryId, @RequestParam("p") Integer page, @RequestParam("s") Integer size, Model model) {
		Sery sery = seryService.findById(seryId);
		List<Blog> blogs = blogService.findBySery(sery, page, size);
		Long bcounts = blogService.getCount(sery);
		model.addAttribute("blogs", blogs);
		model.addAttribute("count", bcounts);
		model.addAttribute("cpage", page);
		return "";
	}

}
