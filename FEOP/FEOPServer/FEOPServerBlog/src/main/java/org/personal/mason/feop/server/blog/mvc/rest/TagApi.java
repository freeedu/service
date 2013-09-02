package org.personal.mason.feop.server.blog.mvc.rest;

import java.util.ArrayList;
import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Tag;
import org.personal.mason.feop.server.blog.domain.service.TagService;
import org.personal.mason.feop.server.blog.mvc.model.TagModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class TagApi {
	private TagService tagService;

	@Autowired
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	/**
	 * 
	 * @param tagName
	 * @return
	 */
	@RequestMapping(value = "/tag/create", method = RequestMethod.GET)
	@ResponseBody
	public TagModel createTag(@RequestParam("n") String tagName) {
		Tag tag = tagService.findByTagName(tagName);
		if (tag == null) {
			tag = new Tag();
			tag.setTagName(tagName);
			tagService.save(tag);
		}
		return TagModel.revert(tag);
	}

	/**
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/tag/list", method = RequestMethod.GET)
	@ResponseBody
	public List<TagModel> findTags(@RequestParam("q") String query) {
		if(query!= null && !query.contains("%")){
			query = query + "%";
		}
		List<Tag> tags = tagService.findByTagNameLike(query);
		List<TagModel> models = new ArrayList<>();
		if (tags != null) {
			for (Tag tag : tags) {
				TagModel model = TagModel.revert(tag);
				models.add(model);
			}
		}
		return models;
	}
}
