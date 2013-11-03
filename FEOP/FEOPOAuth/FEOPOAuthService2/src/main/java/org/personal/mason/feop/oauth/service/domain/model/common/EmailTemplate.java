package org.personal.mason.feop.oauth.service.domain.model.common;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "email_template")
public class EmailTemplate extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 9187789630779122218L;

	private String name;
	private Integer version;
	private String subject;
	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

}