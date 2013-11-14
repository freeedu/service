package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.mvc.model.Address;

public interface ContactAddressService {

	Address createAddress(Long accountId, Long contactId, Address address);

	Address updateAddress(Long accountId, Long contactId, Address address);

	void deleteAddress(Long accountId, Long contactId, Address address);

}
