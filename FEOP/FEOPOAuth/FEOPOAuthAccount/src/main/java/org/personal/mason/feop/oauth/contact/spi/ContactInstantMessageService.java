package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.domain.model.ContactInstantMessage;
import org.personal.mason.feop.oauth.contact.mvc.model.IMVO;

public interface ContactInstantMessageService extends BaseService<IMVO, ContactInstantMessage> {

    IMVO createIM(IMVO view);

    IMVO updateIM(IMVO view);

    void deleteIM(IMVO view);

    IMVO findWithId(Long id);

}
