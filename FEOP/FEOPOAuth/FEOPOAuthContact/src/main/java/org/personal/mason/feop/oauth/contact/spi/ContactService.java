package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.domain.model.Contact;
import org.personal.mason.feop.oauth.contact.mvc.model.ContactVO;
import org.personal.mason.feop.oauth.contact.mvc.model.RecordVO;
import org.personal.mason.feop.oauth.contact.mvc.model.ResourceVO;

import java.util.Date;
import java.util.List;

public interface ContactService extends BaseService<ContactVO, Contact> {

    void deleteContact(ContactVO contact);

    ContactVO createContact(ContactVO contact);

    ContactVO updateContact(ContactVO contact);

    ContactVO findWithId(Long contactId);

    List<ContactVO> findMyContacts(Long contactId);

    List<ContactVO> findContactsWithAccountAndDate(Long accountId, Date date);

    List<ContactVO> findUpdateContacts(Long accountId);

    List<ContactVO> findContactsWithAccountAndRecord(Long accountId, RecordVO record);

    List<ContactVO> findContactsWithAccountAndResource(Long accountId, ResourceVO resource);

    List<ContactVO> queryContactWithAccountAndQuery(Long accountId, String query);

    List<ContactVO> findContactsAccountMightKnow(Long accoutId);
}
