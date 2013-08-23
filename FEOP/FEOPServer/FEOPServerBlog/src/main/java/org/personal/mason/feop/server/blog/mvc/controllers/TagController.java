package org.personal.mason.feop.server.blog.mvc.controllers;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Tag;
import org.personal.mason.feop.server.blog.domain.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TagController {

	private TagService tagService;

	@Autowired
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	@RequestMapping(value = "tag/create", method = RequestMethod.GET)
	public void createTag(@RequestParam("n") String tagName) {
		Tag tag = tagService.findByTagName(tagName);
		if (tag == null) {
			tag = new Tag();
			tag.setTagName(tagName);
			tagService.save(tag);
		}
	}

	@RequestMapping(value="tag/list")
	public String findTags(@RequestParam("q") String query, Model model) {
		List<Tag> tags = tagService.findByTagNameLike(query);
		model.addAttribute("tags", tags);
		return "";
	}
}
