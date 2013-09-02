package org.personal.mason.feop.server.blog.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * The persistent class for the tags database table.
 * 
 */
@Entity
@Table(name = "tags")
public class Tag extends BlogPersistable {
	private static final long serialVersionUID = 1L;

	@Column(name = "tag_name")
	private String tagName;

	@ManyToMany(mappedBy = "tags")
	private List<Blog> blogs;

	public Tag() {
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

}