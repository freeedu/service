package org.personal.mason.feop.oauth.service.repository;

import java.util.Date;
import java.util.List;

import org.personal.mason.feop.oauth.service.domain.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.String;

public interface PasswordResetRepository extends JpaRepository<PasswordReset, Long> {

	List<PasswordReset> findByRequestTimeBefore(Date date);

	List<PasswordReset> findByToken(String token);
}
