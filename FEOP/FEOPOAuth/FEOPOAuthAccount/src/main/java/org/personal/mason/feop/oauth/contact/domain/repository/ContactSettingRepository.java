package org.personal.mason.feop.oauth.contact.domain.repository;

import org.personal.mason.feop.oauth.contact.domain.model.ContactSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactSettingRepository extends JpaRepository<ContactSetting, Long> {

}
