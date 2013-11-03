package org.personal.mason.feop.oauth.common.domain.spi;

import java.util.List;

import org.personal.mason.feop.oauth.common.domain.model.AccountUser;
import org.personal.mason.feop.oauth.common.domain.model.UserPhone;

public interface UserPhoneService {
	UserPhone findDefaultPhone(AccountUser account);

	List<UserPhone> findAllPhones(AccountUser account);

	void savePhone(UserPhone userPhone);

	UserPhone findPhoneById(Long PhoneId);

	void deletePhone(UserPhone userPhone);

	UserPhone updateUserPhone(UserPhone userPhone);

	boolean switchDefault(UserPhone defaultPhone, UserPhone newDefaultPhone);
}
