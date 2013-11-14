package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.mvc.model.Contact;

public interface ContactService {

	void deleteContact(Long accountId, Contact contact);

	Contact createContact(Long accountId, Contact contact);

	Contact updateContact(Long accountId, Contact contact);

}
