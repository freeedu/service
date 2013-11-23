package org.personal.mason.feop.oauth.contact.spi.impl;

import org.personal.mason.feop.oauth.contact.domain.model.ContactRecord;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactRecordRepository;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactRepository;
import org.personal.mason.feop.oauth.contact.mvc.model.RecordVO;
import org.personal.mason.feop.oauth.contact.spi.ContactRecordService;
import org.personal.mason.feop.oauth.contact.utils.ModelConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactRecordServiceImpl implements ContactRecordService {

    private ContactRepository contactRepository;
    private ContactRecordRepository contactRecordRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Autowired
    public void setContactRecordRepository(ContactRecordRepository contactRecordRepository) {
        this.contactRecordRepository = contactRecordRepository;
    }

    @Override
    public RecordVO toViewObject(ContactRecord model) {
        RecordVO recordView = ModelConvertor.toRecordView(model);
        return recordView;
    }

    @Override
    public ContactRecord toModel(RecordVO view) {
        ContactRecord record;
        if (view.getId() == null) {
            record = new ContactRecord();
            if (view.getContact() != null) {
                record.setContact(contactRepository.findOne(view.getContact()));
            }
        } else {
            record = contactRecordRepository.findOne(view.getId());
        }
        ModelConvertor.mergeToRecordModel(view, record);
        return record;
    }

    @Override
    public RecordVO createRecord(RecordVO view) {
        ContactRecord model = toModel(view);
        ContactRecord saved = contactRecordRepository.save(model);
        return toViewObject(saved);
    }

    @Override
    public RecordVO updateRecord(RecordVO view) {
        ContactRecord model = toModel(view);
        ContactRecord updated = contactRecordRepository.saveAndFlush(model);
        return toViewObject(updated);
    }

    @Override
    public void deleteRecord(RecordVO view) {
        if (contactRecordRepository.exists(view.getId())) {
            contactRecordRepository.delete(view.getId());
        }
    }

    @Override
    public RecordVO findWithId(Long id) {
        ContactRecord record = contactRecordRepository.findOne(id);
        if (record != null) {
            return toViewObject(record);
        }
        return null;
    }

}
