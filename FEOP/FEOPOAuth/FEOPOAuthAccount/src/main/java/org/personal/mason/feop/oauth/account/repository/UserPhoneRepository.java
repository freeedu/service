package org.personal.mason.feop.oauth.account.repository;

import org.personal.mason.feop.oauth.account.domain.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.personal.mason.feop.oauth.account.domain.AccountUser;
import java.util.List;

public interface UserPhoneRepository extends JpaRepository<UserPhone, Long> {
	List<UserPhone> findByAccountUser(AccountUser accountuser);

	List<UserPhone> findByAccountUserAndPrimary(AccountUser accountUser, Boolean primary);
}
