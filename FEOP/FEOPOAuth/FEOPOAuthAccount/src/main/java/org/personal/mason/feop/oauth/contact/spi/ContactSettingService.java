package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.mvc.model.Setting;

public interface ContactSettingService {

	Setting createSetting(Long accountId, Long contactId, Setting setting);

	Setting updateSetting(Long accountId, Long contactId, Setting setting);

	void deleteSetting(Long accountId, Long contactId, Setting setting);

}
