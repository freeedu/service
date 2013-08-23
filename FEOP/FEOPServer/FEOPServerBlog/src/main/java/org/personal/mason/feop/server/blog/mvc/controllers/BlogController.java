package org.personal.mason.feop.server.blog.mvc.controllers;

import java.security.Principal;
import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSetting;
import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.model.Sery;
import org.personal.mason.feop.server.blog.domain.service.BlogService;
import org.personal.mason.feop.server.blog.domain.service.CategoryService;
import org.personal.mason.feop.server.blog.domain.service.SeryService;
import org.personal.mason.feop.server.blog.mvc.model.BlogModel;
import org.personal.mason.feop.server.blog.mvc.utils.PrincipalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.personal.mason.feop.server.blog.mvc.utils.ViewMapper;
import org.personal.mason.feop.server.blog.utils.TimeUtils;

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

	/* Public Section of Blog operations */
	@RequestMapping(value = "blog/view", method = RequestMethod.GET)
	public String viewBlog(@RequestParam("id") Long id, Model model) {
		Blog blog = blogService.findById(id);
		BlogModel blogModel = BlogModel.revert(blog);

		model.addAttribute("blog", blogModel);
		return ViewMapper.Blog_View.getViewName();
	}

	@RequestMapping(value = "blog/newest")
	public String getNewestBlogs(@RequestParam(value = "p", required = false) Integer page,
			@RequestParam(value = "l", required = false) Integer size, Model model) {
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
		return ViewMapper.Index.getViewName();
	}

	@RequestMapping(value = "blog/search")
	public String searchBlogs(@RequestParam("q") String query, @RequestParam(value = "p", required = false) Integer page,
			@RequestParam(value = "l", required = false) Integer size, Model model) {
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
		return ViewMapper.Blog_List.getViewName();
	}

	@RequestMapping(value = { "blog", "blog/list" }, method = RequestMethod.GET)
	public String findBlog(@RequestParam(value = "p", required = false) Integer page, @RequestParam(value = "l", required = false) Integer size,
			@RequestParam(value = "c", required = false) Long categoryId, @RequestParam(value = "s", required = false) Long seryId, Model model) {
		if (page == null || page < 0) {
			page = 0;
		}

		if (size == null || size < 0) {
			size = 10;
		}
		List<Blog> blogs = null;
		if (categoryId != null) {
			Category cat = categoryService.findById(categoryId);
			blogs = blogService.findByCategory(cat, page, size);
		} else if (seryId != null) {
			Sery sery = seryService.findById(seryId);
			blogs = blogService.findBySery(sery, page, size);
		} else {
			blogs = blogService.findAll(page, size);
		}
		Long bcounts = blogService.getCount();
		model.addAttribute("blogs", blogs);
		model.addAttribute("count", bcounts);
		model.addAttribute("cpage", page);
		return ViewMapper.Blog_List.getViewName();
	}

	/* Private Section of Blog Operations */
	@RequestMapping(value = "m/blog/create", method = RequestMethod.GET)
	public String createBlog(@ModelAttribute BlogModel blogModel) {
		return ViewMapper.Blog_New.getViewName();
	}

	@RequestMapping(value = "m/blog/create", method = RequestMethod.POST)
	public String saveBlog(@Validated BlogModel blogModel, BindingResult result, Principal principal) {
		if (principal == null || principal.getName().isEmpty()) {
			return "redirect:" + ViewMapper.Login.getViewName();
		}

		if (result.hasErrors()) {
			return null;
		}

		Blog blog = new Blog();
		blog.setCreateDate(TimeUtils.getCurrentTimestamp());
		BlogModel.merge(blog, blogModel);
		blog.setBlogSetting(new BlogSetting());
		if (blogModel.getCategoryId() != null) {
			Category category = categoryService.findById(blogModel.getCategoryId());
			blog.setCategory(category);
		}

		if (blogModel.getSeryId() != null) {
			Sery sery = seryService.findById(blogModel.getSeryId());
			blog.setSery(sery);
		}

		blog.setAuthorName(PrincipalUtils.getUserName(principal));
		blog.setAuthorUid(PrincipalUtils.getUid(principal));
		blogService.save(blog);

		return "redirect:/blog/view?id=" + blog.getId();
	}

	@RequestMapping(value = "m/blog/update", method = RequestMethod.GET)
	public String updateBlog(@RequestParam("id") Long id, Model model) {
		Blog blog = blogService.findById(id);

		BlogModel blogModel = BlogModel.revert(blog);
		model.addAttribute("blogModel", blogModel);
		return ViewMapper.Blog_Edit.getViewName();
	}

	@RequestMapping(value = "m/blog/update", method = RequestMethod.PUT)
	public String updateBlog(@Validated BlogModel blogModel, BindingResult result, Principal principal, Model model) {
		if (principal == null || principal.getName().isEmpty()) {
			return "redirect:/login";
		}

		if (result.hasErrors()) {
			return null;
		}

		Blog blog = new Blog();
		BlogModel.merge(blog, blogModel);
		blog.setAuthorName(PrincipalUtils.getUserName(principal));
		blog.setAuthorUid(PrincipalUtils.getUid(principal));
		Blog updatedBlog = blogService.update(blog);
		BlogModel updatedBlogModel = BlogModel.revert(updatedBlog);
		model.addAttribute("blog", updatedBlogModel);
		return "redirect:/blog/view?id=" + blog.getId();
	}

	@RequestMapping(value = "m/blog/delete", method = RequestMethod.DELETE)
	public void deleteBlog(@RequestParam("id") Long id) {
		blogService.delete(id);
	}

	@RequestMapping(value = { "m/blog/list" }, method = RequestMethod.GET)
	public String findMyBlog(Principal principal, @RequestParam(value = "p", required = false) Integer page,
			@RequestParam(value = "l", required = false) Integer size, Model model) {
		if (page == null || page < 0) {
			page = 0;
		}

		if (size == null || size < 0) {
			size = 10;
		}

		String uid = PrincipalUtils.getUid(principal);
		List<Blog> blogs = blogService.findByAuthorUid(uid, page, size);
		Long bcounts = blogService.getCount(uid);
		model.addAttribute("blogs", blogs);
		model.addAttribute("count", bcounts);
		model.addAttribute("cpage", page);
		return ViewMapper.Blog_My_List.getViewName();
	}

}
