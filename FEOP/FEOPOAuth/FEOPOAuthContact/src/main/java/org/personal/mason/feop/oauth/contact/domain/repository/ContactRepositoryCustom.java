package org.personal.mason.feop.oauth.contact.domain.repository;

import org.personal.mason.feop.oauth.contact.domain.model.Contact;
import org.personal.mason.feop.oauth.contact.domain.model.ContactRecord;
import org.personal.mason.feop.oauth.contact.domain.model.ContactResource;

import java.util.Date;
import java.util.List;

public interface ContactRepositoryCustom {

    List<Contact> getByContactAndContactRemindDateRemindDate(Contact contact, Date date);

    List<Contact> getByContactAndContactRecord(Contact contact, ContactRecord record);

    List<Contact> getByContactAndContactResource(Contact contact, ContactResource resource);

    List<Contact> getUpdateContacts(Contact contact);

    List<Contact> getByContactAndQuery(Contact contact, String query);

    List<Contact> getContactMightKnown(Contact contact);
}
