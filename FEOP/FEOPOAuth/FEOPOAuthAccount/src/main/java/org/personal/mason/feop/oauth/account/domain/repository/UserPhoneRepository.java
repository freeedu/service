package org.personal.mason.feop.oauth.account.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.personal.mason.feop.oauth.account.domain.model.AccountUser;
import org.personal.mason.feop.oauth.account.domain.model.UserPhone;

import java.util.List;

public interface UserPhoneRepository extends JpaRepository<UserPhone, Long> {
	List<UserPhone> findByAccountUser(AccountUser accountuser);

	List<UserPhone> findByAccountUserAndPrimary(AccountUser accountUser, Boolean primary);
}
