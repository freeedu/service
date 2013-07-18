package org.personal.mason.feop.server.blog.mvc.controllers;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.Blog;
import org.personal.mason.feop.server.blog.domain.BlogSection;
import org.personal.mason.feop.server.blog.domain.Common;
import org.personal.mason.feop.server.blog.mvc.model.CommonModel;
import org.personal.mason.feop.server.blog.spi.BlogSectionService;
import org.personal.mason.feop.server.blog.spi.BlogService;
import org.personal.mason.feop.server.blog.spi.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommonController {

	private CommonService commonService;
	private BlogService blogService;
	private BlogSectionService blogSectionService;

	@Autowired
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	@Autowired
	public void setBlogSectionService(BlogSectionService blogSectionService) {
		this.blogSectionService = blogSectionService;
	}

	@Autowired
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	@RequestMapping(value = "common/create", method = RequestMethod.GET)
	public void createCommon(Model model) {
		model.addAttribute("newcommon", new CommonModel());
	}

	@RequestMapping(value = "common/save", method = RequestMethod.POST)
	public void saveCommon(@Validated CommonModel commonModel, BindingResult result) {
		if (result.hasErrors()) {
			return;
		}

		if (commonModel.getBlogId() == null && commonModel.getBlogSectionId() == null && commonModel.getCommonId() == null) {
			result.rejectValue("*", "common.create.invalid", "common should at blog, blogsection or common");
			return;
		}

		Common common = CommonModel.convert(commonModel);
		if (commonModel.getBlogId() != null) {
			Blog blog = blogService.findById(commonModel.getBlogId());
			common.setBlog(blog);
		}

		if (commonModel.getBlogSectionId() != null) {
			BlogSection section = blogSectionService.findById(commonModel.getBlogSectionId());
			common.setBlogSection(section);
			common.setBlog(section.getBlog());
		}

		if (commonModel.getCommonId() != null) {
			Common pc = commonService.findById(commonModel.getCommonId());
			common.setCommon(pc);
			common.setBlogSection(pc.getBlogSection());
			common.setBlog(pc.getBlog());
		}

		commonService.save(common);
	}

	@RequestMapping(value = "common/requry", method = RequestMethod.GET)
	public void findBlogCommons(@RequestParam("bid") Long blogId, @RequestParam("p") Integer page, @RequestParam("s") Integer size, Model model) {
		Blog blog = blogService.findById(blogId);
		List<Common> commons = commonService.findByBlog(blog, page, size);
		Long count = commonService.count(blog);
		model.addAttribute("commons", commons);
		model.addAttribute("count", count);
		model.addAttribute("cpage", page);
	}

	@RequestMapping(value = "common/requry", method = RequestMethod.GET)
	public void findBlogSectionCommons(@RequestParam("sid") Long sectionId, @RequestParam("p") Integer page, @RequestParam("s") Integer size,
			Model model) {
		BlogSection section = blogSectionService.findById(sectionId);
		List<Common> commons = commonService.findByBlogSection(section, page, size);
		Long count = commonService.count(section);
		model.addAttribute("commons", commons);
		model.addAttribute("count", count);
		model.addAttribute("cpage", page);
	}

	@RequestMapping(value = "common/requry", method = RequestMethod.GET)
	public void findCommonCommons(@RequestParam("cid") Long commonId, @RequestParam("p") Integer page, @RequestParam("s") Integer size, Model model) {
		Common pc = commonService.findById(commonId);
		List<Common> commons = commonService.findByCommon(pc, page, size);
		Long count = commonService.count(pc);
		model.addAttribute("commons", commons);
		model.addAttribute("count", count);
		model.addAttribute("cpage", page);
	}
}