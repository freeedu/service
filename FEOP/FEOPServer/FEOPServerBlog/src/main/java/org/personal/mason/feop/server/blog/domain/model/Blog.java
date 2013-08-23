package org.personal.mason.feop.server.blog.domain.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the blog database table.
 * 
 */
@Entity
@Table(name = "blog")
public class Blog extends BlogPersistable {

	private static final long serialVersionUID = 6125866755835969296L;

	@Column(name = "author_name")
	private String authorName;

	@Column(name = "author_uid")
	private String authorUid;

	@Column(name = "blog_desc")
	private String blogDesc;

	@Column(name = "blog_subtitle")
	private String blogSubtitle;

	@Column(name = "blog_title")
	private String blogTitle;

	@Column(name = "create_date")
	private Timestamp createDate;

	@Column(name = "last_update")
	private Timestamp lastUpdate;

	@Column(name = "tag_ids")
	private String tagIds;

	// bi-directional many-to-one association to Sery
	@ManyToOne
	@JoinColumn(name = "series_id")
	private Sery sery;

	// bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	@OneToOne(cascade={CascadeType.ALL})
	@MapsId
	private BlogSetting blogSetting;

	// bi-directional many-to-one association to BlogSection
	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
	private List<BlogSection> blogSections;

	// bi-directional many-to-one association to Comment
	@OneToMany(mappedBy = "blog")
	private List<Comment> comments;

	public Blog() {
	}

	public String getAuthorName() {
		return this.authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorUid() {
		return this.authorUid;
	}

	public void setAuthorUid(String authorUid) {
		this.authorUid = authorUid;
	}

	public String getBlogDesc() {
		return this.blogDesc;
	}

	public void setBlogDesc(String blogDesc) {
		this.blogDesc = blogDesc;
	}

	public String getBlogSubtitle() {
		return this.blogSubtitle;
	}

	public void setBlogSubtitle(String blogSubtitle) {
		this.blogSubtitle = blogSubtitle;
	}

	public String getBlogTitle() {
		return this.blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getTagIds() {
		return this.tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public Sery getSery() {
		return this.sery;
	}

	public void setSery(Sery sery) {
		this.sery = sery;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BlogSetting getBlogSetting() {
		return this.blogSetting;
	}

	public void setBlogSetting(BlogSetting blogSetting) {
		this.blogSetting = blogSetting;
	}

	public List<BlogSection> getBlogSections() {
		return this.blogSections;
	}

	public void setBlogSections(List<BlogSection> blogSections) {
		this.blogSections = blogSections;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}