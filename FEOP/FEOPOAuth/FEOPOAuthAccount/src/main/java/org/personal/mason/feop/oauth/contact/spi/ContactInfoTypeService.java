package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.mvc.model.InfoType;

public interface ContactInfoTypeService {

void deleteInfoType(Long accountId, Long contactId, InfoType infoType);

InfoType createInfoType(Long accountId, Long contactId, InfoType infoType);

}
