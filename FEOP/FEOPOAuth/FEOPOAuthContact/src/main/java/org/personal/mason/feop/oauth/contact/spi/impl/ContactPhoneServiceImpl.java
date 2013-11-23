package org.personal.mason.feop.oauth.contact.spi.impl;

import org.personal.mason.feop.oauth.contact.domain.model.ContactPhone;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactPhoneRepository;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactRepository;
import org.personal.mason.feop.oauth.contact.mvc.model.PhoneVO;
import org.personal.mason.feop.oauth.contact.spi.ContactPhoneService;
import org.personal.mason.feop.oauth.contact.utils.ModelConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactPhoneServiceImpl implements ContactPhoneService {

    private ContactRepository contactRepository;
    private ContactPhoneRepository contactPhoneRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Autowired
    public void setContactPhoneRepository(ContactPhoneRepository contactPhoneRepository) {
        this.contactPhoneRepository = contactPhoneRepository;
    }

    @Override
    public PhoneVO toViewObject(ContactPhone model) {
        PhoneVO phoneView = ModelConvertor.toPhoneView(model);
        return phoneView;
    }

    @Override
    public ContactPhone toModel(PhoneVO view) {
        ContactPhone phone;
        if (view.getId() == null) {
            phone = new ContactPhone();
            if (view.getContact() != null) {
                phone.setContact(contactRepository.findOne(view.getContact()));
            }
        } else {
            phone = contactPhoneRepository.findOne(view.getId());
        }
        ModelConvertor.mergeToPhoneModel(view, phone);
        return phone;
    }

    @Override
    public PhoneVO createPhone(PhoneVO view) {
        ContactPhone model = toModel(view);
        ContactPhone saved = contactPhoneRepository.save(model);
        return toViewObject(saved);
    }

    @Override
    public PhoneVO updatePhone(PhoneVO view) {
        ContactPhone model = toModel(view);
        ContactPhone updated = contactPhoneRepository.saveAndFlush(model);
        return toViewObject(updated);
    }

    @Override
    public void deletePhone(PhoneVO view) {
        if (contactPhoneRepository.exists(view.getId())) {
            contactPhoneRepository.delete(view.getId());
        }
    }

    @Override
    public PhoneVO findWithId(Long id) {
        ContactPhone phone = contactPhoneRepository.findOne(id);
        if (phone != null) {
            return toViewObject(phone);
        }
        return null;
    }

}
