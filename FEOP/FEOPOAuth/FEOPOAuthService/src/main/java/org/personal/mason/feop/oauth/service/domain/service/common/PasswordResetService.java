package org.personal.mason.feop.oauth.service.domain.service.common;

import org.personal.mason.feop.oauth.service.domain.model.common.PasswordReset;

import java.util.Date;
import java.util.List;

public interface PasswordResetService {

    void save(PasswordReset pwdReset);

    PasswordReset findByToken(String token);

    List<PasswordReset> findByDateBefore(Date date);

    void delete(PasswordReset... pwdReset);
}
