package org.personal.mason.feop.oauth.service.domain.repository.common;

import org.personal.mason.feop.oauth.service.domain.model.common.SystemSettings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemSettingsRepository extends JpaRepository<SystemSettings, Long> {

    List<SystemSettings> findByKey(String key);

}
