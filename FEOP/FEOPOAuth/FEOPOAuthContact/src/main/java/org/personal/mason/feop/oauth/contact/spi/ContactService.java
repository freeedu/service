package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.domain.model.Contact;
import org.personal.mason.feop.oauth.contact.mvc.model.ContactVO;

public interface ContactService extends BaseService<ContactVO, Contact> {

    void deleteContact(ContactVO contact);

    ContactVO createContact(ContactVO contact);

    ContactVO updateContact(ContactVO contact);

    ContactVO findWithId(Long contactId);

}
