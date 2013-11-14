package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.mvc.model.Record;

public interface ContactRecordService {

	Record createRecord(Long accountId, Long contactId, Record record);

	Record updateRecord(Long accountId, Long contactId, Record record);

	void deleteRecord(Long accountId, Long contactId, Record record);

}
