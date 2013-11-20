package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.domain.model.ContactAddress;
import org.personal.mason.feop.oauth.contact.mvc.model.AddressVO;

public interface ContactAddressService extends BaseService<AddressVO, ContactAddress> {

AddressVO createAddress(AddressVO view);

AddressVO updateAddress(AddressVO view);

void deleteAddress(AddressVO view);

AddressVO findWithId(Long id);

}
