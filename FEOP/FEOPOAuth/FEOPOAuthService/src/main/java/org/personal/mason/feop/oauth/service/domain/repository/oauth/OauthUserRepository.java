package org.personal.mason.feop.oauth.service.domain.repository.oauth;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.lang.String;

public interface OauthUserRepository extends JpaRepository<OauthUser, Long> {

	@Query("From OauthUser Where email = :ctx Or userName = :ctx")
	List<OauthUser> findByUserNameOrEmail(@Param("ctx") String compareStr);
	
	List<OauthUser> findByUserId(String userid);
	
}