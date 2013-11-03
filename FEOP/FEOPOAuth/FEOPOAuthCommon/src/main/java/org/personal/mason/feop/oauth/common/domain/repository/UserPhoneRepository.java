package org.personal.mason.feop.oauth.common.domain.repository;

import java.util.List;

import org.personal.mason.feop.oauth.common.domain.model.AccountUser;
import org.personal.mason.feop.oauth.common.domain.model.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPhoneRepository extends JpaRepository<UserPhone, Long> {
	List<UserPhone> findByAccountUser(AccountUser accountuser);

	List<UserPhone> findByAccountUserAndPrimary(AccountUser accountUser, Boolean primary);
}
