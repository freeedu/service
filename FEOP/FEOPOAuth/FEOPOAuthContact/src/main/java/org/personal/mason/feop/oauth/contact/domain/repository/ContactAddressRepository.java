package org.personal.mason.feop.oauth.contact.domain.repository;

import org.personal.mason.feop.oauth.contact.domain.model.ContactAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactAddressRepository extends JpaRepository<ContactAddress, Long> {

}
