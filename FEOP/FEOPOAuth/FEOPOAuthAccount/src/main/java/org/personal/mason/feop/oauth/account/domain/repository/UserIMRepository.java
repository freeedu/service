package org.personal.mason.feop.oauth.account.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.personal.mason.feop.oauth.account.domain.model.AccountUser;
import org.personal.mason.feop.oauth.account.domain.model.UserIM;

import java.util.List;

public interface UserIMRepository extends JpaRepository<UserIM, Long> {
	List<UserIM> findByAccountUser(AccountUser accountuser);

	List<UserIM> findByAccountUserAndPrimary(AccountUser accountUser, Boolean primary);
}
