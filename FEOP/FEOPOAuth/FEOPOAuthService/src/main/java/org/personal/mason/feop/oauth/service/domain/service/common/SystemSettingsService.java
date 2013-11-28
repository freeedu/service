package org.personal.mason.feop.oauth.service.domain.service.common;

import org.personal.mason.feop.oauth.service.domain.model.common.SystemSettings;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SystemSettingsService {
    void save(SystemSettings settings);

    SystemSettings update(SystemSettings settings);

    Page<SystemSettings> findAll(int page, int size);

    void delete(SystemSettings... settings);

    SystemSettings findById(Long id);

    List<SystemSettings> findByKey(String key);
}
