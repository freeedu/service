package org.personal.mason.feop.server.blog.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the blog_settings database table.
 * 
 */
@Entity
@Table(name = "blog_settings")
public class BlogSetting extends BlogPersistable {
	private static final long serialVersionUID = 1L;

	private int price;

	@Column(name = "pub_privilege")
	private boolean pubPrivilege;

	// bi-directional many-to-one association to Blog
	@OneToMany(mappedBy = "blogSettingBean")
	private List<Blog> blogs;

	public BlogSetting() {
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean getPubPrivilege() {
		return this.pubPrivilege;
	}

	public void setPubPrivilege(boolean pubPrivilege) {
		this.pubPrivilege = pubPrivilege;
	}

	public List<Blog> getBlogs() {
		return this.blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

}