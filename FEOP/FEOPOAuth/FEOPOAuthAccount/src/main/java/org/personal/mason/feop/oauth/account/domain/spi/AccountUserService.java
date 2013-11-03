package org.personal.mason.feop.oauth.account.domain.spi;

import java.util.Date;
import java.util.List;

import org.personal.mason.feop.oauth.account.domain.model.AccountUser;

public interface AccountUserService {

	AccountUser findById(Long id);
	
	AccountUser findUserByUserId(String userId);
	
	List<AccountUser> findUsersByBirth(Date date);

	void createAccount(AccountUser accountUser);

	AccountUser updateAccount(AccountUser accountUser);
	
}