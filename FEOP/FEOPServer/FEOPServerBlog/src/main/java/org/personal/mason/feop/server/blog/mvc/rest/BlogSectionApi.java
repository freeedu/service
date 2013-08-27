package org.personal.mason.feop.server.blog.mvc.rest;

import java.util.ArrayList;
import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;
import org.personal.mason.feop.server.blog.domain.service.BlogSectionService;
import org.personal.mason.feop.server.blog.domain.service.BlogService;
import org.personal.mason.feop.server.blog.mvc.model.BlogSectionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class BlogSectionApi {

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

	/**
	 * 
	 * @param section
	 * @return
	 */
	@RequestMapping(value = "/section/save", method = RequestMethod.POST)
	@ResponseBody
	public BlogSection createSection(BlogSectionModel section) {
		BlogSection blogSection = BlogSectionModel.convert(section);
		Blog blog = blogService.findById(section.getBlogId());
		blogSectionService.save(blog, blogSection);

		return blogSection;
	}

	/**
	 * 
	 * @param sectionModel
	 * @return
	 */
	@RequestMapping(value = "/section/update", method = RequestMethod.PUT)
	@ResponseBody
	public BlogSectionModel updateBlog(BlogSectionModel sectionModel) {
		BlogSection section = BlogSectionModel.convert(sectionModel);
		BlogSection updatedBlogSection = blogSectionService.update(section);

		BlogSectionModel updatedBlogSectionModel = BlogSectionModel.revert(updatedBlogSection);
		return updatedBlogSectionModel;
	}

	/**
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/section/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteSection(@RequestParam("id") Long id) {
		blogSectionService.delete(id);
	}

	/**
	 * 
	 * @param blogId
	 * @return
	 */
	@RequestMapping(value = "/section/query", method = RequestMethod.GET)
	@ResponseBody
	public List<BlogSectionModel> findBlogSection(@RequestParam("bid") Long blogId) {
		Blog blog = blogService.findById(blogId);
		List<BlogSection> sections = blogSectionService.findByBlog(blog);
		List<BlogSectionModel> models = new ArrayList<>();

		if (sections != null) {
			for (BlogSection section : sections) {
				BlogSectionModel model = BlogSectionModel.revert(section);
				models.add(model);
			}
		}
		return models;
	}
}
