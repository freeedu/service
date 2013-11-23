package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.domain.model.ContactInfoType;
import org.personal.mason.feop.oauth.contact.mvc.model.InfoTypeVO;

import java.util.List;

public interface ContactInfoTypeService extends BaseService<InfoTypeVO, ContactInfoType> {

    void deleteInfoType(Long id);

    InfoTypeVO createInfoType(InfoTypeVO view);

    InfoTypeVO findWithId(Long id);

    List<InfoTypeVO> allTypes();

}
