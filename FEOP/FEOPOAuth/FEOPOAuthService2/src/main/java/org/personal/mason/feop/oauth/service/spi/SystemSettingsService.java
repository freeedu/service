package org.personal.mason.feop.oauth.service.spi;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.SystemSettings;
import org.springframework.data.domain.Page;

public interface SystemSettingsService {
	void save(SystemSettings settings);

	SystemSettings update(SystemSettings settings);

	Page<SystemSettings> findAll(int page, int size);

	void delete(SystemSettings... settings);

	SystemSettings findById(Long id);
	
	List<SystemSettings> findByKey(String key);
}
