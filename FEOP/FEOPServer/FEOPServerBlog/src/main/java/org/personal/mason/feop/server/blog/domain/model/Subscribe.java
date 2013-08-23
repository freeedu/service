package org.personal.mason.feop.server.blog.domain.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the subscribe database table.
 * 
 */
@Entity
@Table(name = "subscribe")
public class Subscribe extends BlogPersistable {

	private static final long serialVersionUID = -5377859563284098288L;

	@Column(name = "create_date")
	private Timestamp createDate;

	@Column(name = "enable")
	private boolean enable;

	@Column(name = "subscriber_uid", unique = true)
	private String subscriberUid;

	@Column(name = "update_time")
	private Timestamp updateTime;

	@Column(name = "blog_ids")
	private String blogIds;

	@Column(name = "category_ids")
	private String categoryIds;

	@Column(name = "sery_ids")
	private String seryIds;

	public Subscribe() {
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getSubscriberUid() {
		return this.subscriberUid;
	}

	public void setSubscriberUid(String subscriberUid) {
		this.subscriberUid = subscriberUid;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getBlogIds() {
		return blogIds;
	}

	public void setBlogIds(String blogIds) {
		this.blogIds = blogIds;
	}

	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}

	public String getSeryIds() {
		return seryIds;
	}

	public void setSeryIds(String seryIds) {
		this.seryIds = seryIds;
	}

}