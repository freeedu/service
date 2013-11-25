package org.personal.mason.feop.oauth.contact.domain.repository;

import org.personal.mason.feop.oauth.contact.domain.model.Contact;
import org.personal.mason.feop.oauth.contact.domain.model.ContactRecord;
import org.personal.mason.feop.oauth.contact.domain.model.ContactResource;

import java.util.Date;
import java.util.List;

public interface ContactRepositoryCustom {

    List<Contact> findByContactAndContactRemindDateRemindDate(Contact contact, Date date);

    List<Contact> findByContactAndContactRecord(Contact contact, ContactRecord record);

    List<Contact> findByContactAndContactResource(Contact contact, ContactResource resource);

    List<Contact> findUpdateContacts(Contact contact);

    List<Contact> findByContactAndQuery(Contact contact, String query);

    List<Contact> findContactMightKnown(Contact contact);
}
