package org.personal.mason.feop.server.blog.mvc.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSetting;
import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.model.Sery;
import org.personal.mason.feop.server.blog.domain.service.BlogService;
import org.personal.mason.feop.server.blog.domain.service.CategoryService;
import org.personal.mason.feop.server.blog.domain.service.SeryService;
import org.personal.mason.feop.server.blog.mvc.model.BlogModel;
import org.personal.mason.feop.server.blog.mvc.utils.AuthenticationUtils;
import org.personal.mason.feop.server.blog.mvc.utils.ViewMapper;
import org.personal.mason.feop.server.blog.utils.TimeUtils;
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

	/* Public Section of Blog operations */
	@RequestMapping(value = "/blog/view", method = RequestMethod.GET)
	public String viewBlog(@RequestParam("id") Long id, Model model) {
		Blog blog = blogService.findById(id);
		BlogModel blogModel = BlogModel.revert(blog);

		model.addAttribute("blog", blogModel);
		return ViewMapper.Blog_View.getViewName();
	}

	@RequestMapping(value = "/blog/newest")
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

	@RequestMapping(value = "/blog/search")
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

	@RequestMapping(value = { "/blog", "/blog/list" }, method = RequestMethod.GET)
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
	@RequestMapping(value = "/my/blog/create", method = RequestMethod.GET)
	public String createBlog(@ModelAttribute BlogModel blogModel) {
		return ViewMapper.Blog_New.getViewName();
	}

	@RequestMapping(value = "/my/blog/create", method = RequestMethod.POST)
	public String saveBlog(@Validated BlogModel blogModel, BindingResult result, HttpServletRequest request) {
		String uid = AuthenticationUtils.getUid(request);
		if (uid == null) {
			return String.format("redirect:%s", "/user/login");
		}

		if (result.hasErrors()) {
			return null;
		}

		Blog blog = new Blog();
		blog.setCreateDate(TimeUtils.getCurrentTimestamp());
		BlogModel.merge(blog, blogModel);
		blog.setBlogSetting(new BlogSetting());
		if (blogModel.getCategory() != null) {
			Category category = categoryService.findById(blogModel.getCategory().getId());
			blog.setCategory(category);
		}

		if (blogModel.getSeryId() != null) {
			Sery sery = seryService.findById(blogModel.getSeryId());
			blog.setSery(sery);
		}

		blog.setAuthorName(AuthenticationUtils.getUserName(request));
		blog.setAuthorUid(AuthenticationUtils.getUid(request));
		blogService.save(blog);

		return "redirect:/blog/view?id=" + blog.getId();
	}

	@RequestMapping(value = "/my/blog/update", method = RequestMethod.GET)
	public String updateBlog(@RequestParam("id") Long id, Model model) {
		Blog blog = blogService.findById(id);

		BlogModel blogModel = BlogModel.revert(blog);
		model.addAttribute("blogModel", blogModel);
		return ViewMapper.Blog_Edit.getViewName();
	}

	@RequestMapping(value = "/my/blog/update", method = RequestMethod.PUT)
	public String updateBlog(@Validated BlogModel blogModel, BindingResult result, HttpServletRequest request, Model model) {
		String uid = AuthenticationUtils.getUid(request);
		if (uid == null) {
			return String.format("redirect:%s", "/user/login");
		}

		if (result.hasErrors()) {
			return null;
		}

		Blog blog = new Blog();
		BlogModel.merge(blog, blogModel);
		blog.setAuthorName(AuthenticationUtils.getUserName(request));
		blog.setAuthorUid(AuthenticationUtils.getUid(request));
		Blog updatedBlog = blogService.update(blog);
		BlogModel updatedBlogModel = BlogModel.revert(updatedBlog);
		model.addAttribute("blog", updatedBlogModel);
		return "redirect:/blog/view?id=" + blog.getId();
	}

	@RequestMapping(value = "/my/blog/delete", method = RequestMethod.DELETE)
	public void deleteBlog(HttpServletRequest request, @RequestParam("id") Long id) {
		String uid = AuthenticationUtils.getUid(request);
		if (uid != null)
			blogService.delete(id);
	}

	@RequestMapping(value = { "/my/blog/list" }, method = RequestMethod.GET)
	public String findMyBlog(HttpServletRequest request, @RequestParam(value = "p", required = false) Integer page,
			@RequestParam(value = "l", required = false) Integer size, Model model) {
		if (page == null || page < 0) {
			page = 0;
		}

		if (size == null || size < 0) {
			size = 10;
		}

		String uid = AuthenticationUtils.getUid(request);
		List<Blog> blogs = blogService.findByAuthorUid(uid, page, size);
		Long bcounts = blogService.getCount(uid);
		model.addAttribute("blogs", blogs);
		model.addAttribute("count", bcounts);
		model.addAttribute("cpage", page);
		return ViewMapper.Blog_My_List.getViewName();
	}

}
