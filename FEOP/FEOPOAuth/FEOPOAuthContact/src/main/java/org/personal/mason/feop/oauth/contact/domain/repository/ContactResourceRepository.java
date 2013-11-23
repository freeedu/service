package org.personal.mason.feop.oauth.contact.domain.repository;

import org.personal.mason.feop.oauth.contact.domain.model.ContactResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactResourceRepository extends JpaRepository<ContactResource, Long> {

}
