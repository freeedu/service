package org.personal.mason.feop.server.blog.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the commons database table.
 * 
 */
@Entity
@Table(name = "commons")
public class Common extends BlogPersistable {
	private static final long serialVersionUID = 1L;

	private String author;

	@Column(name = "author_uid")
	private String authorUid;

	@Lob
	@Column(name = "common_content")
	private String commonContent;

	@Column(name = "create_time")
	private Timestamp createTime;

	private String email;

	private int status;

	// bi-directional many-to-one association to BlogSection
	@ManyToOne
	@JoinColumn(name = "blog_section_id")
	private BlogSection blogSection;

	// bi-directional many-to-one association to Blog
	@ManyToOne
	private Blog blog;

	// bi-directional many-to-one association to Common
	@ManyToOne
	@JoinColumn(name = "at_common_id")
	private Common common;

	// bi-directional many-to-one association to Common
	@OneToMany(mappedBy = "common")
	private List<Common> commons;

	public Common() {
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorUid() {
		return this.authorUid;
	}

	public void setAuthorUid(String authorUid) {
		this.authorUid = authorUid;
	}

	public String getCommonContent() {
		return this.commonContent;
	}

	public void setCommonContent(String commonContent) {
		this.commonContent = commonContent;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BlogSection getBlogSection() {
		return this.blogSection;
	}

	public void setBlogSection(BlogSection blogSection) {
		this.blogSection = blogSection;
	}

	public Blog getBlog() {
		return this.blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Common getCommon() {
		return this.common;
	}

	public void setCommon(Common common) {
		this.common = common;
	}

	public List<Common> getCommons() {
		return this.commons;
	}

	public void setCommons(List<Common> commons) {
		this.commons = commons;
	}

}