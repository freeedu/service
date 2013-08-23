package org.personal.mason.feop.server.blog.mvc.model;

import org.personal.mason.feop.server.blog.domain.model.Blog;

public class BlogModel {

	private Long id;
	private String authorName;
	private String authorUid;
	private String blogDesc;
	private String blogSubtitle;
	private String blogTitle;
	private String tagIds;
	private Long seryId;
	private Long categoryId;

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

	public static void merge(Blog blog, BlogModel model) {
		blog.setBlogTitle(model.getBlogTitle());
		blog.setBlogSubtitle(model.getBlogSubtitle());
		blog.setBlogDesc(model.getBlogDesc());
	}

	public static BlogModel revert(Blog blog) {
		BlogModel model = new BlogModel();
		model.setId(blog.getId());
		model.setAuthorName(blog.getAuthorName());
		model.setAuthorUid(blog.getAuthorUid());
		model.setBlogTitle(blog.getBlogTitle());
		model.setBlogSubtitle(blog.getBlogSubtitle());
		model.setBlogDesc(blog.getBlogDesc());
		model.setTagIds(blog.getTagIds());
		
		if(blog.getSery() != null){
			model.setSeryId(blog.getSery().getId());
		}
		
		if(blog.getCategory() !=null){
			model.setCategoryId(blog.getCategory().getId());
		}
		
		return model;
	}
}
