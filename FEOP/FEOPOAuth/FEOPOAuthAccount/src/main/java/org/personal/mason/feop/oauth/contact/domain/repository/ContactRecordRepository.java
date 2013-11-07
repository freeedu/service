package org.personal.mason.feop.oauth.contact.domain.repository;

import org.personal.mason.feop.oauth.contact.domain.model.ContactRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRecordRepository extends JpaRepository<ContactRecord, Long> {

}
