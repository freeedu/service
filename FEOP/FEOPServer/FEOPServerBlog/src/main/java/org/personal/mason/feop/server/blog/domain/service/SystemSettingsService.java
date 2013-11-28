package org.personal.mason.feop.server.blog.domain.service;

import org.personal.mason.feop.server.blog.domain.model.SystemSettings;

import java.util.List;

public interface SystemSettingsService {

    void save(SystemSettings... settings);

    void delete(SystemSettings... settings);

    List<SystemSettings> findByProfile(String profile);

    List<SystemSettings> update(SystemSettings... settings);

}
