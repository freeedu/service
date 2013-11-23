package org.personal.mason.feop.oauth.contact.domain.repository;

import org.personal.mason.feop.oauth.contact.domain.model.AccountBasic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountBasicRepository extends JpaRepository<AccountBasic, Long> {

    List<AccountBasic> findByAccountUid(String accountuid);

    List<AccountBasic> findByOauthUid(String oauthuid);

}
