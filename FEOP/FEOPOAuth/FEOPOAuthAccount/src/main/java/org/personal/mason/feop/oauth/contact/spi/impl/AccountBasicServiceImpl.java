package org.personal.mason.feop.oauth.contact.spi.impl;

import org.personal.mason.feop.oauth.contact.mvc.model.Account;
import org.personal.mason.feop.oauth.contact.mvc.model.Contact;
import org.personal.mason.feop.oauth.contact.mvc.model.Device;
import org.personal.mason.feop.oauth.contact.spi.AccountBasicService;

public class AccountBasicServiceImpl implements AccountBasicService {

	@Override
	public boolean isExistAccountWithOauthUid(String accountUid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account registAccount(Device device, String accountUid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccountWithOauthUidAndId(String oauthUid, Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccountWithId(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact findMyContact(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}


}
