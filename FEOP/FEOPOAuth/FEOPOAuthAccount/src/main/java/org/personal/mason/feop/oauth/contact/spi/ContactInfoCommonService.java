package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.domain.model.ContactInfoCommon;
import org.personal.mason.feop.oauth.contact.mvc.model.InfoCommonVO;

public interface ContactInfoCommonService extends BaseService<InfoCommonVO, ContactInfoCommon> {

InfoCommonVO createInfoCommon(InfoCommonVO view);

InfoCommonVO updateInfoCommon(InfoCommonVO view);

void deleteInfoCommon(InfoCommonVO view);

InfoCommonVO findWithId(Long id);

}
