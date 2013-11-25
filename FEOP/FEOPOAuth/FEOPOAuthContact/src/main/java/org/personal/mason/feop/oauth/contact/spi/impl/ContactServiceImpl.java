package org.personal.mason.feop.oauth.contact.spi.impl;

import org.personal.mason.feop.oauth.contact.domain.model.*;
import org.personal.mason.feop.oauth.contact.domain.repository.*;
import org.personal.mason.feop.oauth.contact.mvc.model.*;
import org.personal.mason.feop.oauth.contact.spi.ContactService;
import org.personal.mason.feop.oauth.contact.utils.ModelConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private AccountBasicRepository accountBasicRepository;
    private ContactRepository contactRepository;
    private ContactAddressRepository contactAddressRepository;
    private ContactEmailRepository contactEmailRepository;
    private ContactInstantMessageRepository contactInstantMessageRepository;
    private ContactPhoneRepository contactPhoneRepository;
    private ContactRecordRepository contactRecordRepository;
    private ContactResourceRepository contactResourceRepository;
    private ContactRemindDateRepository contactRemindDateRepository;
    private ContactSettingRepository contactSettingRepository;

    @Autowired
    public void setAccountBasicRepository(AccountBasicRepository accountBasicRepository) {
        this.accountBasicRepository = accountBasicRepository;
    }

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void deleteContact(ContactVO contact) {
        if (contactRepository.exists(contact.getId())) {
            contactRepository.delete(contact.getId());
        }
    }

    @Override
    public ContactVO createContact(ContactVO view) {
        Contact contact = toModel(view);
        Contact saved = contactRepository.save(contact);
        return toViewObject(saved);
    }

    @Override
    public ContactVO updateContact(ContactVO view) {
        Contact contact = toModel(view);
        Contact updated = contactRepository.saveAndFlush(contact);
        return toViewObject(updated);
    }

    @Override
    public ContactVO toViewObject(Contact model) {
        ContactVO view = ModelConvertor.toContactView(model);

        for (ContactAddress address : model.getContactAddresses()) {
            view.getAddresses().add(ModelConvertor.toAddressView(address));
        }

        for (ContactEmail email : model.getContactEmails()) {
            view.getEmails().add(ModelConvertor.toEmailView(email));
        }

        for (ContactInstantMessage im : model.getContactInstantMessages()) {
            view.getIms().add(ModelConvertor.toIMView(im));
        }

        for (ContactPhone phone : model.getContactPhones()) {
            view.getPhones().add(ModelConvertor.toPhoneView(phone));
        }

        for (ContactRecord record : model.getContactRecords()) {
            view.getRecords().add(ModelConvertor.toRecordView(record));
        }

        for (ContactRemindDate remindDate : model.getContactRemindDates()) {
            view.getRemindDates().add(ModelConvertor.toRemindDateView(remindDate));
        }

        for (ContactResource resource : model.getContactResources()) {
            view.getResources().add(ModelConvertor.toResourceView(resource));
        }

        for (Contact contact : model.getContacts()) {
            view.getContacts().add(ModelConvertor.toContactView(contact));
        }

        for (ContactSetting setting : model.getContactSettings()) {
            view.getSettinngs().add(ModelConvertor.toSettingView(setting));
        }

        return view;
    }

    @Override
    public Contact toModel(ContactVO view) {
        Contact contact;
        if (view.getId() == null) {
            contact = new Contact();
        } else {
            contact = contactRepository.findOne(view.getId());
        }

        ModelConvertor.mergeToContactModel(view, contact);

        for (AddressVO addressView : view.getAddresses()) {
            ContactAddress address;
            if (addressView.getId() == null) {
                address = new ContactAddress();
            } else {
                address = contactAddressRepository.findOne(addressView.getId());
            }
            ModelConvertor.mergeToAddressModel(addressView, address);
            address.setContact(contact);
            contact.getContactAddresses().add(address);
        }

        for (ContactVO contactView : view.getContacts()) {
            Contact contactModel;
            if (contactView.getId() == null) {
                contactModel = new Contact();
            } else {
                contactModel = contactRepository.findOne(contactView.getId());
            }
            ModelConvertor.mergeToContactModel(contactView, contactModel);
            contactModel.setContact(contact);
            contact.getContacts().add(contactModel);
        }

        for (EmailVO emailView : view.getEmails()) {
            ContactEmail email;
            if (emailView.getId() == null) {
                email = new ContactEmail();
            } else {
                email = contactEmailRepository.findOne(emailView.getId());
            }
            ModelConvertor.mergeToEmailModel(emailView, email);
            email.setContact(contact);
            contact.getContactEmails().add(email);
        }

        for (IMVO imView : view.getIms()) {
            ContactInstantMessage im;
            if (imView.getId() == null) {
                im = new ContactInstantMessage();
            } else {
                im = contactInstantMessageRepository.findOne(imView.getId());
            }
            ModelConvertor.mergeToIMModel(imView, im);
            im.setContact(contact);
            contact.getContactInstantMessages().add(im);
        }

        for (PhoneVO phoneView : view.getPhones()) {
            ContactPhone phone;
            if (phoneView.getId() == null) {
                phone = new ContactPhone();
            } else {
                phone = contactPhoneRepository.findOne(phoneView.getId());
            }
            ModelConvertor.mergeToPhoneModel(phoneView, phone);
            phone.setContact(contact);
            contact.getContactPhones().add(phone);
        }

        for (RecordVO recordView : view.getRecords()) {
            ContactRecord record;
            if (recordView.getId() == null) {
                record = new ContactRecord();
            } else {
                record = contactRecordRepository.findOne(recordView.getId());
            }
            ModelConvertor.mergeToRecordModel(recordView, record);
            record.setContact(contact);
            contact.getContactRecords().add(record);
        }

        for (RemindDateVO remindDateView : view.getRemindDates()) {
            ContactRemindDate remindDate;
            if (remindDateView.getId() == null) {
                remindDate = new ContactRemindDate();
            } else {
                remindDate = contactRemindDateRepository.findOne(remindDateView.getId());
            }
            ModelConvertor.mergeToRemindDateModel(remindDateView, remindDate);
            remindDate.setContact(contact);
            contact.getContactRemindDates().add(remindDate);
        }

        for (ResourceVO resourceView : view.getResources()) {
            ContactResource resource;
            if (resourceView.getId() == null) {
                resource = new ContactResource();
            } else {
                resource = contactResourceRepository.findOne(resourceView.getId());
            }
            ModelConvertor.mergeToResourceModel(resourceView, resource);
            resource.setContact(contact);
            ;
            contact.getContactResources().add(resource);
        }

        for (SettingVO settingView : view.getSettinngs()) {
            ContactSetting setting;
            if (settingView.getId() == null) {
                setting = new ContactSetting();
            } else {
                setting = contactSettingRepository.findOne(settingView.getId());
            }
            ModelConvertor.mergeToSettingModel(settingView, setting);
            setting.setContact(contact);
            contact.getContactSettings().add(setting);

        }
        return contact;
    }

    @Override
    public ContactVO findWithId(Long contactId) {
        Contact contact = contactRepository.findOne(contactId);
        if (contact != null) {
            return toViewObject(contact);
        }
        return null;
    }

    @Override
    public List<ContactVO> findContactsWithAccountAndDate(Long accountId, Date date) {
        List<ContactVO> views = new ArrayList<>();
        AccountBasic accountBasic = accountBasicRepository.findOne(accountId);
        if(accountId != null){
            List<Contact> contacts = contactRepository.findByContactAndContactRemindDateRemindDate(accountBasic.getContact(), date);
            for (Contact contact : contacts){
                ContactVO view = toViewObject(contact);
                views.add(view);
            }
        }
        return views;
    }

    @Override
    public List<ContactVO> findUpdateContacts(Long accountId) {
        List<ContactVO> views = new ArrayList<>();
        AccountBasic accountBasic = accountBasicRepository.findOne(accountId);
        if(accountId != null){
            List<Contact> contacts = contactRepository.findUpdateContacts(accountBasic.getContact());
            for (Contact contact : contacts){
                ContactVO view = toViewObject(contact);
                views.add(view);
            }
        }
        return views;
    }

    @Override
    public List<ContactVO> findContactsWithAccountAndRecord(Long accountId, RecordVO recordVO) {
        List<ContactVO> views = new ArrayList<>();
        AccountBasic accountBasic = accountBasicRepository.findOne(accountId);
        if(accountId != null){
            ContactRecord record = new ContactRecord();
            ModelConvertor.mergeToRecordModel(recordVO, record);
            List<Contact> contacts = contactRepository.findByContactAndContactRecord(accountBasic.getContact(), record);
            for (Contact contact : contacts){
                ContactVO view = toViewObject(contact);
                views.add(view);
            }
        }
        return views;
    }

    @Override
    public List<ContactVO> findContactsWithAccountAndResource(Long accountId, ResourceVO resourceVO) {
        List<ContactVO> views = new ArrayList<>();
        AccountBasic accountBasic = accountBasicRepository.findOne(accountId);
        if(accountId != null){
            ContactResource resource = new ContactResource();
            ModelConvertor.mergeToResourceModel(resourceVO, resource);
            List<Contact> contacts = contactRepository.findByContactAndContactResource(accountBasic.getContact(), resource);
            for (Contact contact : contacts){
                ContactVO view = toViewObject(contact);
                views.add(view);
            }
        }
        return views;
    }

    @Override
    public List<ContactVO> queryContactWithAccountAndQuery(Long accountId, String query) {
        List<ContactVO> views = new ArrayList<>();
        AccountBasic accountBasic = accountBasicRepository.findOne(accountId);
        if(accountId != null){
            List<Contact> contacts = contactRepository.findByContactAndQuery(accountBasic.getContact(), query);
            for (Contact contact : contacts){
                ContactVO view = toViewObject(contact);
                views.add(view);
            }
        }
        return views;
    }

    @Override
    public List<ContactVO> findContactsAccountMightKnow(Long accountId) {
        List<ContactVO> views = new ArrayList<>();
        AccountBasic accountBasic = accountBasicRepository.findOne(accountId);
        if(accountId != null){
            List<Contact> contacts = contactRepository.findContactMightKnown(accountBasic.getContact());
            for (Contact contact : contacts){
                ContactVO view = toViewObject(contact);
                views.add(view);
            }
        }
        return views;
    }

}
