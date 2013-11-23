package org.personal.mason.feop.oauth.service.mvc.model;

import org.personal.mason.feop.oauth.service.domain.model.common.EmailTemplate;

public class EmailTemplateForm {

	private Long id;
	private String name;
	private String content;
	private Integer version;
	private String subject;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public static EmailTemplateForm revert(EmailTemplate template) {
		EmailTemplateForm form = new EmailTemplateForm();
		form.setId(template.getId());
		form.setName(template.getName());
		form.setSubject(template.getSubject());
		form.setVersion(template.getVersion());
		form.setContent(template.getContent());
		return form;
	}
}
