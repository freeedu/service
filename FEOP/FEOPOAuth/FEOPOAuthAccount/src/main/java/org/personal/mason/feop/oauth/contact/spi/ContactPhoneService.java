package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.mvc.model.Phone;

public interface ContactPhoneService {

	Phone createPhone(Long accountId, Long contactId, Phone phone);

	Phone updatePhone(Long accountId, Long contactId, Phone phone);

	void deletePhone(Long accountId, Long contactId, Phone phone);

}
