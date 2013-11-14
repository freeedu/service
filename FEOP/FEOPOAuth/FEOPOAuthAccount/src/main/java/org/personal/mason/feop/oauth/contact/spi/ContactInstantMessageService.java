package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.mvc.model.IM;

public interface ContactInstantMessageService {

	IM createIM(Long accountId, Long contactId, IM im);

	IM updateIM(Long accountId, Long contactId, IM im);

	void deleteIM(Long accountId, Long contactId, IM im);

}
