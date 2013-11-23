package org.personal.mason.feop.oauth.contact.spi.impl;

import org.personal.mason.feop.oauth.contact.domain.model.ContactInfoCommon;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactInfoCommonRepository;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactRepository;
import org.personal.mason.feop.oauth.contact.mvc.model.InfoCommonVO;
import org.personal.mason.feop.oauth.contact.spi.ContactInfoCommonService;
import org.personal.mason.feop.oauth.contact.utils.ModelConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactInfoCommonServiceImpl implements ContactInfoCommonService {

    private ContactRepository contactRepository;
    private ContactInfoCommonRepository contactInfoCommonRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Autowired
    public void setContactInfoCommonRepository(ContactInfoCommonRepository contactInfoCommonRepository) {
        this.contactInfoCommonRepository = contactInfoCommonRepository;
    }

    @Override
    public InfoCommonVO toViewObject(ContactInfoCommon model) {
        InfoCommonVO infoCommonView = ModelConvertor.toInfoCommonView(model);
        return infoCommonView;
    }

    @Override
    public ContactInfoCommon toModel(InfoCommonVO view) {
        ContactInfoCommon infoCommon;
        if (view.getId() == null) {
            infoCommon = new ContactInfoCommon();
            if (view.getContact() != null) {
                infoCommon.setContact(contactRepository.findOne(view.getContact()));
            }
        } else {
            infoCommon = contactInfoCommonRepository.findOne(view.getId());
        }
        ModelConvertor.mergeToInfoCommonModel(view, infoCommon);
        return infoCommon;
    }

    @Override
    public InfoCommonVO createInfoCommon(InfoCommonVO view) {
        ContactInfoCommon model = toModel(view);
        ContactInfoCommon saved = contactInfoCommonRepository.save(model);
        return toViewObject(saved);
    }

    @Override
    public InfoCommonVO updateInfoCommon(InfoCommonVO view) {
        ContactInfoCommon model = toModel(view);
        ContactInfoCommon updated = contactInfoCommonRepository.saveAndFlush(model);
        return toViewObject(updated);
    }

    @Override
    public void deleteInfoCommon(InfoCommonVO view) {
        if (contactInfoCommonRepository.exists(view.getId())) {
            contactInfoCommonRepository.delete(view.getId());
        }
    }

    @Override
    public InfoCommonVO findWithId(Long id) {
        ContactInfoCommon common = contactInfoCommonRepository.findOne(id);
        if (common == null) {
            return toViewObject(common);
        }
        return null;
    }

}
