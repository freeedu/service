package org.personal.mason.feop.server.blog.mvc.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.personal.mason.feop.server.blog.domain.model.Tag;

@XmlRootElement(name = "tag")
@JsonRootName("tag")
public class TagModel {

	private Long id;
	private String tagName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public static TagModel revert(Tag tag) {
		TagModel model = new TagModel();
		model.setId(tag.getId());
		model.setTagName(tag.getTagName());
		return model;
	}

}
