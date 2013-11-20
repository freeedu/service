package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.domain.model.ContactSetting;
import org.personal.mason.feop.oauth.contact.mvc.model.SettingVO;

public interface ContactSettingService extends BaseService<SettingVO, ContactSetting> {

SettingVO createSetting(SettingVO setting);

SettingVO updateSetting(SettingVO setting);

void deleteSetting(SettingVO setting);

SettingVO findWithId(Long id);

}
