package org.personal.mason.feop.oauth.service.domain.repository.oauth;

import org.personal.mason.feop.oauth.service.domain.model.oauth.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PasswordResetRepository extends JpaRepository<PasswordReset, Long> {

    List<PasswordReset> findByRequestTimeBefore(Date date);

    List<PasswordReset> findByToken(String token);
}
