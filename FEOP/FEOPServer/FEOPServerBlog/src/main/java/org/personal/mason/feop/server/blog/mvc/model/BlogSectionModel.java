package org.personal.mason.feop.server.blog.mvc.model;

import org.personal.mason.feop.server.blog.domain.BlogSection;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

	public static BlogSection convert(BlogSectionModel model) {
		throw new NotImplementedException();
	}

	public static BlogSectionModel revert(BlogSection section) {
		throw new NotImplementedException();
	}
}
