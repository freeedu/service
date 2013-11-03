package org.personal.mason.feop.oauth.account.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.personal.mason.feop.oauth.account.domain.model.AccountUser;
import org.personal.mason.feop.oauth.account.domain.model.UserEmail;

import java.util.List;

public interface UserEmailRepository extends JpaRepository<UserEmail, Long> {

	List<UserEmail> findByAccountUser(AccountUser accountuser);

	List<UserEmail> findByAccountUserAndPrimary(AccountUser accountUser, Boolean primary);
}
