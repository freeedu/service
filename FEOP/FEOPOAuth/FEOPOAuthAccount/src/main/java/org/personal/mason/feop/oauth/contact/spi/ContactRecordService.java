package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.domain.model.ContactRecord;
import org.personal.mason.feop.oauth.contact.mvc.model.RecordVO;

public interface ContactRecordService extends BaseService<RecordVO, ContactRecord> {

    RecordVO createRecord(RecordVO view);

    RecordVO updateRecord(RecordVO view);

    void deleteRecord(RecordVO view);

    RecordVO findWithId(Long id);

}
