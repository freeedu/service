package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.domain.model.ContactRemindDate;
import org.personal.mason.feop.oauth.contact.mvc.model.RemindDateVO;

public interface ContactRemindDateService extends BaseService<RemindDateVO, ContactRemindDate> {

    RemindDateVO createRemindDate(RemindDateVO view);

    RemindDateVO updateRemindDate(RemindDateVO view);

    void deleteRemindDate(RemindDateVO view);

    RemindDateVO findWithId(Long id);

}
