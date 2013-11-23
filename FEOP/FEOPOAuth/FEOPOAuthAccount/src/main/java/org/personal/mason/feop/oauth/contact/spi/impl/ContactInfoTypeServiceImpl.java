package org.personal.mason.feop.oauth.contact.spi.impl;

import org.personal.mason.feop.oauth.contact.domain.model.ContactInfoType;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactInfoTypeRepository;
import org.personal.mason.feop.oauth.contact.mvc.model.InfoTypeVO;
import org.personal.mason.feop.oauth.contact.spi.ContactInfoTypeService;
import org.personal.mason.feop.oauth.contact.utils.ModelConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactInfoTypeServiceImpl implements ContactInfoTypeService {

    private ContactInfoTypeRepository contactInfoTypeRepository;

    @Autowired
    public void setContactInfoTypeRepository(ContactInfoTypeRepository contactInfoTypeRepository) {
        this.contactInfoTypeRepository = contactInfoTypeRepository;
    }

    @Override
    public InfoTypeVO toViewObject(ContactInfoType model) {
        InfoTypeVO infoTypeView = ModelConvertor.toInfoTypeView(model);
        return infoTypeView;
    }

    @Override
    public ContactInfoType toModel(InfoTypeVO view) {
        ContactInfoType phone;
        if (view.getId() == null) {
            phone = new ContactInfoType();
        } else {
            phone = contactInfoTypeRepository.findOne(view.getId());
        }
        ModelConvertor.mergeToInfoTypeModel(view, phone);
        return phone;
    }

    @Override
    public void deleteInfoType(Long id) {
        if (contactInfoTypeRepository.exists(id)) {
            contactInfoTypeRepository.delete(id);
        }
    }

    @Override
    public InfoTypeVO createInfoType(InfoTypeVO view) {
        ContactInfoType model = toModel(view);
        ContactInfoType saved = contactInfoTypeRepository.save(model);
        return toViewObject(saved);
    }

    @Override
    public InfoTypeVO findWithId(Long id) {
        ContactInfoType infoType = contactInfoTypeRepository.findOne(id);
        if (infoType != null) {
            return toViewObject(infoType);
        }
        return null;
    }

    @Override
    public List<InfoTypeVO> allTypes() {
        List<InfoTypeVO> types = new ArrayList<>();

        List<ContactInfoType> allTypes = contactInfoTypeRepository.findAll();
        for (ContactInfoType type : allTypes) {
            InfoTypeVO typeView = toViewObject(type);
            types.add(typeView);
        }

        return types;
    }

}
