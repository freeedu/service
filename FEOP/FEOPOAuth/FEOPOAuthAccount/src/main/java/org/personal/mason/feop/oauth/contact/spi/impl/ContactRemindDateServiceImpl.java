package org.personal.mason.feop.oauth.contact.spi.impl;

import org.personal.mason.feop.oauth.contact.domain.model.ContactRemindDate;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactRemindDateRepository;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactRepository;
import org.personal.mason.feop.oauth.contact.mvc.model.RemindDateVO;
import org.personal.mason.feop.oauth.contact.spi.ContactRemindDateService;
import org.personal.mason.feop.oauth.contact.utils.ModelConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactRemindDateServiceImpl implements ContactRemindDateService {

private ContactRepository contactRepository;
private ContactRemindDateRepository contactRemindDateRepository;

@Autowired
public void setContactRepository(ContactRepository contactRepository) {
	this.contactRepository = contactRepository;
}

@Autowired
public void setContactRemindDateRepository(ContactRemindDateRepository contactRemindDateRepository) {
	this.contactRemindDateRepository = contactRemindDateRepository;
}

@Override
public RemindDateVO toViewObject(ContactRemindDate model) {
	RemindDateVO view = ModelConvertor.toRemindDateView(model);
	return view;
}

@Override
public ContactRemindDate toModel(RemindDateVO view) {
	ContactRemindDate remindDate;
	if (view.getId() == null) {
		remindDate = new ContactRemindDate();
		if (view.getContact() != null) {
			remindDate.setContact(contactRepository.findOne(view.getContact()));
		}
	} else {
		remindDate = contactRemindDateRepository.findOne(view.getId());
	}
	ModelConvertor.mergeToRemindDateModel(view, remindDate);
	return remindDate;
}

@Override
public RemindDateVO createRemindDate(RemindDateVO view) {
	ContactRemindDate model = toModel(view);
	ContactRemindDate saved = contactRemindDateRepository.save(model);
	return toViewObject(saved);
}

@Override
public RemindDateVO updateRemindDate(RemindDateVO view) {
	ContactRemindDate remindDate = toModel(view);
	ContactRemindDate updated = contactRemindDateRepository.saveAndFlush(remindDate);
	return toViewObject(updated);
}

@Override
public void deleteRemindDate(RemindDateVO view) {
	if (contactRemindDateRepository.exists(view.getId())) {
		contactRemindDateRepository.delete(view.getId());
	}
}

@Override
public RemindDateVO findWithId(Long id) {
	ContactRemindDate remindDate = contactRemindDateRepository.findOne(id);
	if (remindDate != null) {
		return toViewObject(remindDate);
	}
	return null;
}

}
