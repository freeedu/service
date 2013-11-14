package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.mvc.model.Account;
import org.personal.mason.feop.oauth.contact.mvc.model.Contact;
import org.personal.mason.feop.oauth.contact.mvc.model.Device;

public interface AccountBasicService {

	boolean isExistAccountWithOauthUid(String accountUid);

	Account registAccount(Device device, String accountUid);

	Account findAccountWithOauthUidAndId(String oauthUid, Long accountId);

	Account findAccountWithId(Long accountId);

	Contact findMyContact(Long accountId);

	
}
