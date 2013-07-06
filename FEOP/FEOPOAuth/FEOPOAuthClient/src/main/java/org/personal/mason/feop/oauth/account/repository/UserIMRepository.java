package org.personal.mason.feop.oauth.account.repository;

import org.personal.mason.feop.oauth.account.domain.UserIM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.personal.mason.feop.oauth.account.domain.AccountUser;
import java.util.List;

public interface UserIMRepository extends JpaRepository<UserIM, Long> {
	List<UserIM> findByAccountUser(AccountUser accountuser);

	List<UserIM> findByAccountUserAndPrimary(AccountUser accountUser, Boolean primary);
}
