package org.personal.mason.feop.server.blog.mvc.model;

import java.sql.Timestamp;

import org.personal.mason.feop.server.blog.domain.model.Comment;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CommentModel {
	private Long id;
	private String author;
	private String authorUid;

	private String commentContent;
	private Timestamp createTime;
	private String email;
	private int status;
	private Long blogSectionId;
	private Long blogId;
	private Long commentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getBlogSectionId() {
		return blogSectionId;
	}

	public void setBlogSectionId(Long blogSectionId) {
		this.blogSectionId = blogSectionId;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public static Comment convert(CommentModel model) {
		throw new NotImplementedException();
	}

	public static CommentModel revert(Comment comment) {
		throw new NotImplementedException();
	}
}
