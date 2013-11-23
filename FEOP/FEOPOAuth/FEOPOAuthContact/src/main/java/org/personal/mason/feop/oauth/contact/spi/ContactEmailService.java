package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.domain.model.ContactEmail;
import org.personal.mason.feop.oauth.contact.mvc.model.EmailVO;

public interface ContactEmailService extends BaseService<EmailVO, ContactEmail> {

    EmailVO createEmail(EmailVO view);

    EmailVO updateEmail(EmailVO view);

    void deleteEmail(EmailVO view);

    EmailVO findWithId(Long id);

}
