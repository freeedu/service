package org.personal.mason.feop.server.blog.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * The persistent class for the blog database table.
 * 
 */
@Entity
public class Blog extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1L;

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

	// bi-directional many-to-one association to BlogSetting
	@ManyToOne
	@JoinColumn(name = "blog_setting")
	private BlogSetting blogSettingBean;

	// bi-directional many-to-one association to BlogSection
	@OneToMany(mappedBy = "blog")
	private List<BlogSection> blogSections;

	// bi-directional many-to-one association to Common
	@OneToMany(mappedBy = "blog")
	private List<Common> commons;

	// bi-directional many-to-one association to SubscribeTopic
	@OneToMany(mappedBy = "blog")
	private List<SubscribeTopic> subscribeTopics;

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

	public BlogSetting getBlogSettingBean() {
		return this.blogSettingBean;
	}

	public void setBlogSettingBean(BlogSetting blogSettingBean) {
		this.blogSettingBean = blogSettingBean;
	}

	public List<BlogSection> getBlogSections() {
		return this.blogSections;
	}

	public void setBlogSections(List<BlogSection> blogSections) {
		this.blogSections = blogSections;
	}

	public List<Common> getCommons() {
		return this.commons;
	}

	public void setCommons(List<Common> commons) {
		this.commons = commons;
	}

	public List<SubscribeTopic> getSubscribeTopics() {
		return this.subscribeTopics;
	}

	public void setSubscribeTopics(List<SubscribeTopic> subscribeTopics) {
		this.subscribeTopics = subscribeTopics;
	}

}