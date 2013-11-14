package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.mvc.model.Email;

public interface ContactEmailService {

	Email createEmail(Long accountId, Long contactId, Email email);

	Email updateEmail(Long accountId, Long contactId, Email email);

	void deleteEmail(Long accountId, Long contactId, Email email);

}
