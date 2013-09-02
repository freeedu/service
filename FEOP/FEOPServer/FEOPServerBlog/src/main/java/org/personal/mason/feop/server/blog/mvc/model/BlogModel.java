package org.personal.mason.feop.server.blog.mvc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;
import org.personal.mason.feop.server.blog.domain.model.Tag;

@XmlRootElement(name = "blog")
@JsonRootName("blog")
public class BlogModel {
	private Long id;
	private String authorName;
	private String authorUid;
	private String blogTitle;
	private String blogSubtitle;
	private String blogDesc;
	private Date createDate;
	private Date update;

	private CategoryModel category;
	private BlogSettingModel setting;
	private SeryModel sery;
	private Long read;
	private Long praised;
	private Long criticized;
	private Integer comments;

	private String tagNames;
	private List<TagModel> tags = new ArrayList<>();
	private List<BlogSectionModel> sections = new ArrayList<>();

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

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogSubtitle() {
		return blogSubtitle;
	}

	public void setBlogSubtitle(String blogSubtitle) {
		this.blogSubtitle = blogSubtitle;
	}

	public String getBlogDesc() {
		return blogDesc;
	}

	public void setBlogDesc(String blogDesc) {
		this.blogDesc = blogDesc;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public CategoryModel getCategory() {
		return category;
	}

	public void setCategory(CategoryModel category) {
		this.category = category;
	}

	public BlogSettingModel getSetting() {
		return setting;
	}

	public void setSetting(BlogSettingModel setting) {
		this.setting = setting;
	}

	public SeryModel getSery() {
		return sery;
	}

	public void setSery(SeryModel sery) {
		this.sery = sery;
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

	public String getTagNames() {
		return tagNames;
	}

	public void setTagNames(String tagNames) {
		this.tagNames = tagNames;
	}

	public List<TagModel> getTags() {
		return tags;
	}

	public void setTags(List<TagModel> tags) {
		this.tags = tags;
	}

	public List<BlogSectionModel> getSections() {
		return sections;
	}

	public void setSections(List<BlogSectionModel> sections) {
		this.sections = sections;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
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
		model.setCreateDate(blog.getCreateDate());
		model.setUpdate(blog.getLastUpdate());
		model.setRead(blog.getRead());
		model.setPraised(blog.getPraised());
		model.setCriticized(blog.getCriticized());
		model.setComments(blog.getComments().size());
		if (blog.getCategory() != null) {
			model.setCategory(CategoryModel.revert(blog.getCategory()));
		}
		if (blog.getBlogSetting() != null) {
			model.setSetting(BlogSettingModel.revert(blog.getBlogSetting()));
		}
		if (blog.getSery() != null) {
			model.setSery(SeryModel.revert(blog.getSery()));
		}

		if (blog.getBlogSections() != null) {
			for (BlogSection section : blog.getBlogSections()) {
				BlogSectionModel sectionModel = BlogSectionModel.revert(section);
				model.getSections().add(sectionModel);
			}
		}

		if (blog.getTags() != null) {
			StringBuilder tagNamesBuilder = new StringBuilder();
			for (Tag tag : blog.getTags()) {
				TagModel tagModel = TagModel.revert(tag);
				tagNamesBuilder.append(tagModel.getTagName()).append(", ");
				model.getTags().add(tagModel);
			}
			model.setTagNames(tagNamesBuilder.toString());
		}

		return model;
	}
}
