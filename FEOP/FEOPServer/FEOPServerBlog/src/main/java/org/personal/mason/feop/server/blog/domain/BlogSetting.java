package org.personal.mason.feop.server.blog.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;

/**
 * The persistent class for the blog_settings database table.
 * 
 */
@Entity
@Table(name = "blog_settings")
public class BlogSetting extends AbstractPersistable<Long> {
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