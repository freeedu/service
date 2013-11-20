package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.domain.model.AccountBasic;
import org.personal.mason.feop.oauth.contact.mvc.model.AccountVO;
import org.personal.mason.feop.oauth.contact.mvc.model.ContactVO;
import org.personal.mason.feop.oauth.contact.mvc.model.DeviceVO;

public interface AccountBasicService extends BaseService<AccountVO, AccountBasic> {

boolean isExistAccountWithOauthUid(String accountUid);

AccountVO registAccount(DeviceVO device, String accountUid);

AccountVO findAccountWithOauthUidAndId(String oauthUid, Long accountId);

AccountVO findAccountWithId(Long accountId);

ContactVO findMyContact(Long accountId);

}
