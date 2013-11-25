package org.personal.mason.feop.oauth.contact.domain.repository;

import org.personal.mason.feop.oauth.contact.domain.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long>, ContactRepositoryCustom {

}
