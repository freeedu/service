package org.personal.mason.feop.oauth.contact.spi;

import java.util.List;

import org.personal.mason.feop.oauth.contact.domain.model.ContactInfoType;
import org.personal.mason.feop.oauth.contact.mvc.model.InfoTypeVO;

public interface ContactInfoTypeService extends BaseService<InfoTypeVO, ContactInfoType> {

void deleteInfoType(Long id);

InfoTypeVO createInfoType(InfoTypeVO view);

InfoTypeVO findWithId(Long id);

List<InfoTypeVO> allTypes();

}
