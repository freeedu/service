package org.personal.mason.feop.oauth.account.spi;

import java.util.List;

import org.personal.mason.feop.oauth.account.domain.AccountUser;
import org.personal.mason.feop.oauth.account.domain.UserEmail;

public interface UserEmailService {

	UserEmail findDefaultEmail(AccountUser account);

	List<UserEmail> findAllEmails(AccountUser account);

	void saveEmail(UserEmail userEmail);

	UserEmail findEmailById(Long EmailId);

	void deleteEmail(UserEmail userEmail);

	UserEmail updateUserEmail(UserEmail userEmail);

	boolean switchDefault(UserEmail defaultEmail, UserEmail newDefaultEmail);
}
