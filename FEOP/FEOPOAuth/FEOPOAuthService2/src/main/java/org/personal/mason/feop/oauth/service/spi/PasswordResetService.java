package org.personal.mason.feop.oauth.service.spi;

import java.util.Date;
import java.util.List;

import org.personal.mason.feop.oauth.service.domain.PasswordReset;

public interface PasswordResetService {

	void save(PasswordReset pwdReset);

	PasswordReset findByToken(String token);

	List<PasswordReset> findByDateBefore(Date date);

	void delete(PasswordReset... pwdReset);
}
