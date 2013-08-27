package org.personal.mason.feop.server.blog.domain.service;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.SystemSettings;

public interface SystemSettingsService {

	void save(SystemSettings... settings);

	void delete(SystemSettings... settings);

	List<SystemSettings> findByProfile(String profile);

	List<SystemSettings> update(SystemSettings... settings);

}
