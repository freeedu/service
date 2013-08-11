package org.personal.mason.feop.oauth.service.repository;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.SystemSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.String;

public interface SystemSettingsRepository extends JpaRepository<SystemSettings, Long> {

	List<SystemSettings> findByKey(String key);

}
