package org.personal.mason.feop.oauth.contact.spi.impl;

import org.personal.mason.feop.oauth.contact.domain.model.ContactInstantMessage;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactInstantMessageRepository;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactRepository;
import org.personal.mason.feop.oauth.contact.mvc.model.IMVO;
import org.personal.mason.feop.oauth.contact.spi.ContactInstantMessageService;
import org.personal.mason.feop.oauth.contact.utils.ModelConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactInstantMessageServiceImpl implements ContactInstantMessageService {

    private ContactRepository contactRepository;
    private ContactInstantMessageRepository contactInstantMessageRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Autowired
    public void setContactInstantMessageRepository(
            ContactInstantMessageRepository contactInstantMessageRepository) {
        this.contactInstantMessageRepository = contactInstantMessageRepository;
    }

    @Override
    public IMVO toViewObject(ContactInstantMessage model) {
        IMVO imView = ModelConvertor.toIMView(model);
        return imView;
    }

    @Override
    public ContactInstantMessage toModel(IMVO view) {
        ContactInstantMessage instantMessage;
        if (view.getId() == null) {
            instantMessage = new ContactInstantMessage();
            if (view.getContact() != null) {
                instantMessage.setContact(contactRepository.findOne(view.getContact()));
            }
        } else {
            instantMessage = contactInstantMessageRepository.findOne(view.getId());
        }
        ModelConvertor.mergeToIMModel(view, instantMessage);
        return instantMessage;
    }

    @Override
    public IMVO createIM(IMVO view) {
        ContactInstantMessage model = toModel(view);
        ContactInstantMessage saved = contactInstantMessageRepository.save(model);
        return toViewObject(saved);
    }

    @Override
    public IMVO updateIM(IMVO view) {
        ContactInstantMessage model = toModel(view);
        ContactInstantMessage updated = contactInstantMessageRepository.saveAndFlush(model);
        return toViewObject(updated);
    }

    @Override
    public void deleteIM(IMVO view) {
        if (contactInstantMessageRepository.exists(view.getId())) {
            contactInstantMessageRepository.delete(view.getId());
        }
    }

    @Override
    public IMVO findWithId(Long id) {
        ContactInstantMessage im = contactInstantMessageRepository.findOne(id);
        if (im != null) {
            return toViewObject(im);
        }
        return null;
    }

}
