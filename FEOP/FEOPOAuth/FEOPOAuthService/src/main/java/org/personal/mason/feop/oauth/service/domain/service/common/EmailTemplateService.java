package org.personal.mason.feop.oauth.service.domain.service.common;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.model.common.EmailTemplate;

public interface EmailTemplateService {

	void save(EmailTemplate template);

	EmailTemplate update(EmailTemplate template);

	List<EmailTemplate> findTemplatesByName(String name);

	EmailTemplate findLatestTemplateWithName(String name);

	void delete(EmailTemplate... templates);

	EmailTemplate findById(Long id);

	List<EmailTemplate> findAll();
}
