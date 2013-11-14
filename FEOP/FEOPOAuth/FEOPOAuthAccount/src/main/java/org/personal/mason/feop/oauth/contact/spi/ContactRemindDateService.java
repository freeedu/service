package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.mvc.model.RemindDate;

public interface ContactRemindDateService {

	RemindDate createRemindDate(Long accountId, Long contactId, RemindDate remindDate);

	RemindDate updateRemindDate(Long accountId, Long contactId, RemindDate remindDate);

	void deleteRemindDate(Long accountId, Long contactId, RemindDate remindDate);

}
