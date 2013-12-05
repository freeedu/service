package org.personal.mason.feop.oauth.common.domain.repository;

import org.personal.mason.feop.oauth.common.domain.model.SystemSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemSettingRepository extends JpaRepository<SystemSetting, Long> {

    List<SystemSetting> findByProfile(String profile);

    List<SystemSetting> findByKey(String key);

}
