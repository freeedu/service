package org.personal.mason.feop.oauth.contact.spi.impl;

import org.personal.mason.feop.oauth.contact.domain.model.ContactEmail;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactEmailRepository;
import org.personal.mason.feop.oauth.contact.domain.repository.ContactRepository;
import org.personal.mason.feop.oauth.contact.mvc.model.EmailVO;
import org.personal.mason.feop.oauth.contact.spi.ContactEmailService;
import org.personal.mason.feop.oauth.contact.utils.ModelConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactEmailServiceImpl implements ContactEmailService {
private ContactRepository contactRepository;
private ContactEmailRepository contactEmailRepository;

@Autowired
public void setContactRepository(ContactRepository contactRepository) {
	this.contactRepository = contactRepository;
}

@Autowired
public void setContactEmailRepository(ContactEmailRepository contactEmailRepository) {
	this.contactEmailRepository = contactEmailRepository;
}

@Override
public EmailVO toViewObject(ContactEmail model) {
	EmailVO emailView = ModelConvertor.toEmailView(model);
	return emailView;
}

@Override
public ContactEmail toModel(EmailVO view) {
	ContactEmail email;
	if (view.getId() == null) {
		email = new ContactEmail();
		if (view.getContact() != null) {
			email.setContact(contactRepository.findOne(view.getContact()));
		}
	} else {
		email = contactEmailRepository.findOne(view.getId());
	}
	ModelConvertor.mergeToEmailModel(view, email);
	return email;
}

@Override
public EmailVO createEmail(EmailVO view) {
	ContactEmail model = toModel(view);
	ContactEmail saved = contactEmailRepository.save(model);
	return toViewObject(saved);
}

@Override
public EmailVO updateEmail(EmailVO view) {
	ContactEmail model = toModel(view);
	ContactEmail updated = contactEmailRepository.saveAndFlush(model);
	return toViewObject(updated);
}

@Override
public void deleteEmail(EmailVO view) {
	if (contactEmailRepository.exists(view.getId())) {
		contactEmailRepository.delete(view.getId());
	}
}

@Override
public EmailVO findWithId(Long id) {
	ContactEmail email = contactEmailRepository.findOne(id);
	if (email != null) {
		return toViewObject(email);
	}
	return null;
}

}
