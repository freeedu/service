package org.personal.mason.feop.server.blog.mvc.model;

import java.sql.Timestamp;

import org.personal.mason.feop.server.blog.domain.Blog;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BlogModel {

	private Long id;
	private String authorName;
	private String authorUid;
	private String blogDesc;
	private String blogSubtitle;
	private String blogTitle;
	private Timestamp createDate;
	private Timestamp lastUpdate;
	private String tagIds;
	private Long seryId;
	private Long categoryId;
	private Long blogSettingId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTagIds() {
		return tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public Long getSeryId() {
		return seryId;
	}

	public void setSeryId(Long seryId) {
		this.seryId = seryId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getBlogSettingId() {
		return blogSettingId;
	}

	public void setBlogSettingId(Long blogSettingId) {
		this.blogSettingId = blogSettingId;
	}

	public static Blog convert(BlogModel model) {
		throw new NotImplementedException();
	}

	public static BlogModel revert(Blog blog) {
		throw new NotImplementedException();
	}
}
