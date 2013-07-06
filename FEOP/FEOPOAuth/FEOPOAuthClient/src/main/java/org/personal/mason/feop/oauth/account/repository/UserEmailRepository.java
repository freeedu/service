package org.personal.mason.feop.oauth.account.repository;

import org.personal.mason.feop.oauth.account.domain.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.personal.mason.feop.oauth.account.domain.AccountUser;
import java.util.List;

public interface UserEmailRepository extends JpaRepository<UserEmail, Long> {

	List<UserEmail> findByAccountUser(AccountUser accountuser);

	List<UserEmail> findByAccountUserAndPrimary(AccountUser accountUser, Boolean primary);
}
