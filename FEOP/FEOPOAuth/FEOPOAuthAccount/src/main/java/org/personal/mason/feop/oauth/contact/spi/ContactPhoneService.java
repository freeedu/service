package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.domain.model.ContactPhone;
import org.personal.mason.feop.oauth.contact.mvc.model.PhoneVO;

public interface ContactPhoneService extends BaseService<PhoneVO, ContactPhone> {

    PhoneVO createPhone(PhoneVO view);

    PhoneVO updatePhone(PhoneVO view);

    void deletePhone(PhoneVO view);

    PhoneVO findWithId(Long id);

}
