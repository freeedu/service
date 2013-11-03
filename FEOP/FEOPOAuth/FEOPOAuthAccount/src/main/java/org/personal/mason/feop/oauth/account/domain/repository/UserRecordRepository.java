package org.personal.mason.feop.oauth.account.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.personal.mason.feop.oauth.account.domain.model.AccountUser;
import org.personal.mason.feop.oauth.account.domain.model.UserRecord;

import java.util.List;

public interface UserRecordRepository extends JpaRepository<UserRecord, Long> {
	List<UserRecord> findByAccountUser(AccountUser accountuser);
}
