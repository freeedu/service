package org.personal.mason.feop.server.blog.mvc.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.personal.mason.feop.server.blog.domain.model.Comment;
import org.personal.mason.feop.server.blog.utils.TimeUtils;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@XmlRootElement(name = "comment")
@JsonRootName("comment")
public class CommentModel {
	private Long id;
	private String author;
	private String authorUid;

	private String commentContent;
	private Date createTime;
	private String email;
	private String site;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
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
		Comment comment = new Comment();
		comment.setAuthor(model.getAuthor());
		comment.setCreateTime(TimeUtils.getCurrentTimestamp());
		comment.setEmail(model.getEmail());
		comment.setSite(model.getSite());
		comment.setCommentContent(model.getCommentContent());
		return comment;
	}

	public static CommentModel revert(Comment comment) {
		throw new NotImplementedException();
	}
}
