package org.personal.mason.feop.server.blog.mvc.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.service.BlogService;
import org.personal.mason.feop.server.blog.mvc.model.BlogModel;
import org.personal.mason.feop.server.blog.utils.PageableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ServerController {
	private final static List<Order> DEFAULT_ORDERS;

	static {
		Order createDateDescOrder = new Order(Direction.DESC, "createDate");
		Order lastUpdateDescOrder = new Order(Direction.DESC, "lastUpdate");
		DEFAULT_ORDERS = Collections.unmodifiableList(Arrays.asList(createDateDescOrder, lastUpdateDescOrder));
	}

	private BlogService blogService;

	@Autowired
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	@RequestMapping(value = { "/", "index" }, method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {
		Page<Blog> blogs = blogService.findAll(PageableUtils.getPageable(new PageRequest(1, 5), new Sort(DEFAULT_ORDERS)));

		List<BlogModel> models = new ArrayList<>();
		if (blogs != null) {
			for (Blog blog : blogs) {
				BlogModel blogModel = BlogModel.revert(blog);
				models.add(blogModel);
			}
		}

		model.addAttribute("blogs", models);

		return "app.index";
	}

	@RequestMapping(value = { "errorPage" })
	public String error(@RequestParam(value = "error", required = false) String error, Model model) {
		model.addAttribute("error", error);
		return "app.error";
	}
}
