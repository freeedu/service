package org.personal.mason.feop.oauth.account.domain.repository;

import java.util.List;

import org.personal.mason.feop.oauth.account.domain.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountUserRepository extends JpaRepository<AccountUser, Long> {

	@Query("From AccountUser Where userId = :userId")
	List<AccountUser> findByUserId(String userId);
	
	List<AccountUser> findByBirthMonthDay(String monthAndDay);
	
}
