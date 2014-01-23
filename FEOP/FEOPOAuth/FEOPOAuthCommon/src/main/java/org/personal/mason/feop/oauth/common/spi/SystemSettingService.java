package org.personal.mason.feop.oauth.common.spi;

import org.personal.mason.feop.oauth.common.domain.model.SystemSetting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SystemSettingService {

    void save(SystemSetting... settings);

    void delete(SystemSetting... settings);

    Page<SystemSetting> findAll(Pageable pageable);

    List<SystemSetting> findByProfile(String profile);

    List<SystemSetting> update(SystemSetting... settings);

    SystemSetting findById(Long id);

    List<SystemSetting> findByKey(String key);

}
