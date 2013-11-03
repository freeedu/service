package org.personal.mason.feop.oauth.common.domain.repository;

import java.util.List;

import org.personal.mason.feop.oauth.common.domain.model.AccountUser;
import org.personal.mason.feop.oauth.common.domain.model.UserIM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIMRepository extends JpaRepository<UserIM, Long> {
	List<UserIM> findByAccountUser(AccountUser accountuser);

	List<UserIM> findByAccountUserAndPrimary(AccountUser accountUser, Boolean primary);
}
