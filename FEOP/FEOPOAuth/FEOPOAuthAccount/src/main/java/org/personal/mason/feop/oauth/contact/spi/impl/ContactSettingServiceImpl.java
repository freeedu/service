package org.personal.mason.feop.oauth.contact.spi.impl;

import org.personal.mason.feop.oauth.contact.domain.model.ContactSetting;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactRepository;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactSettingRepository;
import org.personal.mason.feop.oauth.contact.mvc.model.SettingVO;
import org.personal.mason.feop.oauth.contact.spi.ContactSettingService;
import org.personal.mason.feop.oauth.contact.utils.ModelConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactSettingServiceImpl implements ContactSettingService {

private ContactRepository contactRepository;
private ContactSettingRepository contactSettingRepository;

@Autowired
public void setContactRepository(ContactRepository contactRepository) {
	this.contactRepository = contactRepository;
}

@Autowired
public void setContactSettingRepository(ContactSettingRepository contactSettingRepository) {
	this.contactSettingRepository = contactSettingRepository;
}

@Override
public SettingVO createSetting(SettingVO setting) {
	ContactSetting s = toModel(setting);
	ContactSetting saved = contactSettingRepository.save(s);
	return toViewObject(saved);
}

@Override
public SettingVO updateSetting(SettingVO setting) {
	ContactSetting s = toModel(setting);
	ContactSetting updated = contactSettingRepository.saveAndFlush(s);
	return toViewObject(updated);
}

@Override
public void deleteSetting(SettingVO setting) {
	if (contactSettingRepository.exists(setting.getId())) {
		contactSettingRepository.delete(setting.getId());
	}
}

@Override
public SettingVO toViewObject(ContactSetting model) {
	SettingVO vo = ModelConvertor.toSettingView(model);
	return vo;
}

@Override
public ContactSetting toModel(SettingVO view) {
	ContactSetting setting;
	if (view.getId() == null) {
		setting = new ContactSetting();
		if (view.getContact() != null) {
			setting.setContact(contactRepository.findOne(view.getContact()));
		}
	} else {
		setting = contactSettingRepository.findOne(view.getId());
	}
	ModelConvertor.mergeToSettingModel(view, setting);
	return setting;
}

@Override
public SettingVO findWithId(Long id) {
	ContactSetting setting = contactSettingRepository.findOne(id);
	if (setting == null) {
		return toViewObject(setting);
	}
	return null;
}

}
