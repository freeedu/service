package org.personal.mason.feop.oauth.account.repository;

import org.personal.mason.feop.oauth.account.domain.UserResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.personal.mason.feop.oauth.account.domain.AccountUser;
import java.util.List;

public interface UserResourceRepository extends JpaRepository<UserResource, Long> {
	List<UserResource> findByAccountUser(AccountUser accountuser);
}
