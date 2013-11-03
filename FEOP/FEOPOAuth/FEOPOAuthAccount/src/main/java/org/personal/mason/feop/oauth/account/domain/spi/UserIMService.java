package org.personal.mason.feop.oauth.account.domain.spi;

import java.util.List;

import org.personal.mason.feop.oauth.account.domain.model.AccountUser;
import org.personal.mason.feop.oauth.account.domain.model.UserIM;

public interface UserIMService {
	UserIM findDefaultIM(AccountUser account);

	List<UserIM> findAllIMs(AccountUser account);

	void saveIM(UserIM userIM);

	UserIM findIMById(Long IMId);

	void deleteIM(UserIM userIM);

	UserIM updateUserIM(UserIM userIM);

	boolean switchDefault(UserIM defaultIM, UserIM newDefaultIM);
}
