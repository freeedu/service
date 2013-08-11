package org.personal.mason.feop.oauth.service.repository;

import org.personal.mason.feop.oauth.service.domain.EmailTemplate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.String;
import java.util.List;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {

	List<EmailTemplate> findByName(String name);

	List<EmailTemplate> findByName(String name, Sort sort);

}
