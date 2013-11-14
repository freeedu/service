package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.mvc.model.InfoCommon;

public interface ContactInfoCommonService {

	InfoCommon createInfoCommon(Long accountId, Long contactId, InfoCommon infoCommon);

	InfoCommon updateInfoCommon(Long accountId, Long contactId, InfoCommon infoCommon);

	void deleteInfoCommon(Long accountId, Long contactId, InfoCommon infoCommon);

}
