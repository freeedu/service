package org.personal.mason.feop.oauth.account.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.personal.mason.feop.oauth.account.domain.model.AccountUser;
import org.personal.mason.feop.oauth.account.domain.model.UserResource;

import java.util.List;

public interface UserResourceRepository extends JpaRepository<UserResource, Long> {
	List<UserResource> findByAccountUser(AccountUser accountuser);
}
