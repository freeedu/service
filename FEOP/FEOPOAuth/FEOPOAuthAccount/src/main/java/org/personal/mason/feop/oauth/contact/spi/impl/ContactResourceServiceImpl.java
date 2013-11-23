package org.personal.mason.feop.oauth.contact.spi.impl;

import org.personal.mason.feop.oauth.contact.domain.model.ContactResource;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactRepository;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactResourceRepository;
import org.personal.mason.feop.oauth.contact.mvc.model.ResourceVO;
import org.personal.mason.feop.oauth.contact.spi.ContactResourceService;
import org.personal.mason.feop.oauth.contact.utils.ModelConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactResourceServiceImpl implements ContactResourceService {

    private ContactRepository contactRepository;
    private ContactResourceRepository contactResourceRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Autowired
    public void setContactResourceRepository(ContactResourceRepository contactResourceRepository) {
        this.contactResourceRepository = contactResourceRepository;
    }

    @Override
    public ResourceVO toViewObject(ContactResource model) {
        ResourceVO resourceView = ModelConvertor.toResourceView(model);
        return resourceView;
    }

    @Override
    public ContactResource toModel(ResourceVO view) {
        ContactResource resource;
        if (view.getId() == null) {
            resource = new ContactResource();
            if (view.getContact() != null) {
                resource.setContact(contactRepository.findOne(view.getContact()));
            }
        } else {
            resource = contactResourceRepository.findOne(view.getId());
        }
        ModelConvertor.mergeToResourceModel(view, resource);
        return resource;
    }

    @Override
    public ResourceVO createResource(ResourceVO view) {
        ContactResource resource = toModel(view);
        ContactResource saved = contactResourceRepository.save(resource);
        return toViewObject(saved);
    }

    @Override
    public ResourceVO updateResource(ResourceVO view) {
        ContactResource resource = toModel(view);
        ContactResource updated = contactResourceRepository.saveAndFlush(resource);
        return toViewObject(updated);
    }

    @Override
    public void deleteResource(ResourceVO view) {
        if (contactResourceRepository.exists(view.getId())) {
            contactResourceRepository.delete(view.getId());
        }
    }

    @Override
    public ResourceVO findWithId(Long id) {
        ContactResource resource = contactResourceRepository.findOne(id);
        if (resource == null) {
            return toViewObject(resource);
        }
        return null;
    }

}
