package org.personal.mason.feop.oauth.contact.spi.impl;

import org.personal.mason.feop.oauth.contact.domain.model.ContactAddress;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactAddressRepository;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactRepository;
import org.personal.mason.feop.oauth.contact.mvc.model.AddressVO;
import org.personal.mason.feop.oauth.contact.spi.ContactAddressService;
import org.personal.mason.feop.oauth.contact.utils.ModelConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactAddressServiceImpl implements ContactAddressService {

    private ContactRepository contactRepository;
    private ContactAddressRepository contactAddressRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Autowired
    public void setContactAddressRepository(ContactAddressRepository contactAddressRepository) {
        this.contactAddressRepository = contactAddressRepository;
    }

    @Override
    public AddressVO toViewObject(ContactAddress model) {
        AddressVO addressView = ModelConvertor.toAddressView(model);
        return addressView;
    }

    @Override
    public ContactAddress toModel(AddressVO view) {
        ContactAddress address;
        if (view.getId() == null) {
            address = new ContactAddress();
            if (view.getContact() != null) {
                address.setContact(contactRepository.findOne(view.getContact()));
            }
        } else {
            address = contactAddressRepository.findOne(view.getId());
        }
        ModelConvertor.mergeToAddressModel(view, address);
        return address;
    }

    @Override
    public AddressVO createAddress(AddressVO view) {
        ContactAddress model = toModel(view);
        ContactAddress saved = contactAddressRepository.save(model);
        return toViewObject(saved);
    }

    @Override
    public AddressVO updateAddress(AddressVO view) {
        ContactAddress model = toModel(view);
        ContactAddress updated = contactAddressRepository.saveAndFlush(model);
        return toViewObject(updated);
    }

    @Override
    public void deleteAddress(AddressVO view) {
        if (contactAddressRepository.exists(view.getId())) {
            contactAddressRepository.delete(view.getId());
        }
    }

    @Override
    public AddressVO findWithId(Long id) {
        ContactAddress address = contactAddressRepository.findOne(id);
        if (address != null) {
            return toViewObject(address);
        }
        return null;
    }

}
