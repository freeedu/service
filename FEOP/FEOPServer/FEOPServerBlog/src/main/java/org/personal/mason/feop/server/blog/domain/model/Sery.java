package org.personal.mason.feop.server.blog.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the series database table.
 * 
 */
@Entity
@Table(name = "series")
public class Sery extends BlogPersistable {

	private static final long serialVersionUID = 7306683027898517513L;

	@Lob
	@Column(name = "description")
	private String description;

	@Column(name = "series_name")
	private String seriesName;

	// bi-directional many-to-one association to Blog
	@OneToMany(mappedBy = "sery")
	private List<Blog> blogs;

	// bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	public Sery() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSeriesName() {
		return this.seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public List<Blog> getBlogs() {
		return this.blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}