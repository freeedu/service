package org.personal.mason.feop.server.blog.mvc.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;

@XmlRootElement(name = "section")
@JsonRootName("section")
public class BlogSectionModel {

	private Long id;
	private String sectionContent;
	private String sectionTitle;
	private int sequence;
	private Long blogId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSectionContent() {
		return sectionContent;
	}

	public void setSectionContent(String sectionContent) {
		this.sectionContent = sectionContent;
	}

	public String getSectionTitle() {
		return sectionTitle;
	}

	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public static void merge(BlogSection section, BlogSectionModel model) {
		section.setSectionTitle(model.getSectionTitle());
		section.setSectionContent(model.getSectionContent());
	}

	public static BlogSection convert(BlogSectionModel model) {
		BlogSection section = new BlogSection();
		section.setId(model.getId());
		section.setSectionTitle(model.getSectionTitle());
		section.setSectionContent(model.getSectionContent());
		return section;
	}

	public static BlogSectionModel revert(BlogSection section) {
		BlogSectionModel model = new BlogSectionModel();
		model.setBlogId(section.getBlog().getId());
		model.setId(section.getId());
		model.setSectionContent(section.getSectionContent());
		model.setSectionTitle(section.getSectionTitle());
		model.setSequence(section.getSequence());
		return model;
	}
}
