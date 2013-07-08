package org.personal.mason.feop.server.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * The persistent class for the tags database table.
 * 
 */
@Entity
@Table(name = "tags")
public class Tag extends AbstractPersistable<Long> {
	private static final long serialVersionUID = 1L;

	@Column(name = "tag_name")
	private String tagName;

	public Tag() {
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}