package org.personal.mason.feop.server.blog.domain.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

	@Column(name = "blog_desc", length = 1000)
	private String blogDesc;

	@Column(name = "blog_subtitle")
	private String blogSubtitle;

	@Column(name = "blog_title")
	private String blogTitle;

	@Column(name = "create_date")
	private Timestamp createDate;

	@Column(name = "last_update")
	private Timestamp lastUpdate;

	@Column(name = "read_count")
	private Long read = 0l;

	@Column(name = "praised")
	private Long praised = 0l;

	@Column(name = "criticized")
	private Long criticized = 0l;

	// bi-directional many-to-one association to Sery
	@ManyToOne
	@JoinColumn(name = "series_id")
	private Sery sery;

	// bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	@OneToOne(cascade = { CascadeType.ALL })
	private BlogSetting blogSetting;

	// bi-directional many-to-one association to BlogSection
	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
	private List<BlogSection> blogSections;

	// bi-directional many-to-one association to Comment
	@OneToMany(mappedBy = "blog")
	private List<Comment> comments;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "blog_tags", joinColumns = { @JoinColumn(name = "blog_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
	private List<Tag> tags;

	public Blog() {
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorUid() {
		return authorUid;
	}

	public void setAuthorUid(String authorUid) {
		this.authorUid = authorUid;
	}

	public String getBlogDesc() {
		return blogDesc;
	}

	public void setBlogDesc(String blogDesc) {
		this.blogDesc = blogDesc;
	}

	public String getBlogSubtitle() {
		return blogSubtitle;
	}

	public void setBlogSubtitle(String blogSubtitle) {
		this.blogSubtitle = blogSubtitle;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Long getRead() {
		return read;
	}

	public void setRead(Long read) {
		this.read = read;
	}

	public Long getPraised() {
		return praised;
	}

	public void setPraised(Long praised) {
		this.praised = praised;
	}

	public Long getCriticized() {
		return criticized;
	}

	public void setCriticized(Long criticized) {
		this.criticized = criticized;
	}

	public Sery getSery() {
		return sery;
	}

	public void setSery(Sery sery) {
		this.sery = sery;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BlogSetting getBlogSetting() {
		return blogSetting;
	}

	public void setBlogSetting(BlogSetting blogSetting) {
		this.blogSetting = blogSetting;
	}

	public List<BlogSection> getBlogSections() {
		return blogSections;
	}

	public void setBlogSections(List<BlogSection> blogSections) {
		this.blogSections = blogSections;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}