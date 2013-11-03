package org.personal.mason.feop.oauth.common.domain.repository;

import java.util.List;

import org.personal.mason.feop.oauth.common.domain.model.AccountUser;
import org.personal.mason.feop.oauth.common.domain.model.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEmailRepository extends JpaRepository<UserEmail, Long> {

	List<UserEmail> findByAccountUser(AccountUser accountuser);

	List<UserEmail> findByAccountUserAndPrimary(AccountUser accountUser, Boolean primary);
}
