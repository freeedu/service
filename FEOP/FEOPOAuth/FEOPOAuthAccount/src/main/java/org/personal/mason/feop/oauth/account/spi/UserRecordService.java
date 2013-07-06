package org.personal.mason.feop.oauth.account.spi;

import java.util.List;

import org.personal.mason.feop.oauth.account.domain.AccountUser;
import org.personal.mason.feop.oauth.account.domain.UserRecord;

public interface UserRecordService {

	List<UserRecord> findAllRecords(AccountUser account);

	void saveRecord(UserRecord userRecord);

	UserRecord findRecordById(Long recordId);

	void deleteRecord(UserRecord phone);

	UserRecord updateUserRecord(UserRecord email);

}
