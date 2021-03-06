package org.personal.mason.feop.oauth.service.domain.service.common.impl;

import org.personal.mason.feop.oauth.service.domain.model.common.EmailTemplate;
import org.personal.mason.feop.oauth.service.domain.repository.common.EmailTemplateRepository;
import org.personal.mason.feop.oauth.service.domain.service.common.EmailTemplateService;
import org.personal.mason.feop.oauth.service.utils.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

    private EmailTemplateRepository emailTemplateRepository;

    @Autowired
    public void setEmailTemplateRepository(EmailTemplateRepository emailTemplateRepository) {
        this.emailTemplateRepository = emailTemplateRepository;
    }

    @Override
    @Transactional
    public void save(EmailTemplate template) {
        emailTemplateRepository.save(template);
    }

    @Override
    @Transactional
    public EmailTemplate update(EmailTemplate template) {
        return emailTemplateRepository.saveAndFlush(template);
    }

    @Override
    @Transactional
    public List<EmailTemplate> findTemplatesByName(String name) {
        return emailTemplateRepository.findByName(name);
    }

    @Override
    @Transactional
    public EmailTemplate findLatestTemplateWithName(String name) {
        List<EmailTemplate> templates = emailTemplateRepository.findByName(name, SortUtils.getSortDESC("version"));
        return (templates != null && templates.size() > 0) ? templates.get(0) : null;
    }

    @Override
    @Transactional
    public void delete(EmailTemplate... templates) {
        emailTemplateRepository.delete(Arrays.asList(templates));
    }

    @Override
    @Transactional
    public EmailTemplate findById(Long id) {
        return emailTemplateRepository.findOne(id);
    }

    @Override
    @Transactional
    public List<EmailTemplate> findAll() {
        return emailTemplateRepository.findAll();
    }

}
