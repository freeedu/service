package org.personal.mason.feop.oauth.common.domain.repository;

import java.util.List;

import org.personal.mason.feop.oauth.common.domain.model.AccountUser;
import org.personal.mason.feop.oauth.common.domain.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRecordRepository extends JpaRepository<UserRecord, Long> {
	List<UserRecord> findByAccountUser(AccountUser accountuser);
}
