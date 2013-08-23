package org.personal.mason.feop.server.blog.domain.model;

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
@Table(name = "comment")
public class Comment extends BlogPersistable {
	private static final long serialVersionUID = 3919216092287646339L;

	private String author;

	@Column(name = "author_uid")
	private String authorUid;

	@Lob
	@Column(name = "comment_content")
	private String commentContent;

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
	@JoinColumn(name = "at_comment_id")
	private Comment comment;

	// bi-directional many-to-one association to Common
	@OneToMany(mappedBy = "comment")
	private List<Comment> comments;

	public Comment() {
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorUid() {
		return authorUid;
	}

	public void setAuthorUid(String authorUid) {
		this.authorUid = authorUid;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BlogSection getBlogSection() {
		return blogSection;
	}

	public void setBlogSection(BlogSection blogSection) {
		this.blogSection = blogSection;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}