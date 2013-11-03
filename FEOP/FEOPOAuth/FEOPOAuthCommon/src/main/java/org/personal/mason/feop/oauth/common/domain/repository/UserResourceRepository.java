package org.personal.mason.feop.oauth.common.domain.repository;

import java.util.List;

import org.personal.mason.feop.oauth.common.domain.model.AccountUser;
import org.personal.mason.feop.oauth.common.domain.model.UserResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserResourceRepository extends JpaRepository<UserResource, Long> {
	List<UserResource> findByAccountUser(AccountUser accountuser);
}
