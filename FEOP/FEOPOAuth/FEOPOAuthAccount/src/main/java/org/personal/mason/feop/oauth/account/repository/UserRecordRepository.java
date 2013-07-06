package org.personal.mason.feop.oauth.account.repository;

import org.personal.mason.feop.oauth.account.domain.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.personal.mason.feop.oauth.account.domain.AccountUser;
import java.util.List;

public interface UserRecordRepository extends JpaRepository<UserRecord, Long> {
	List<UserRecord> findByAccountUser(AccountUser accountuser);
}
