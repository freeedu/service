package org.personal.mason.feop.server.blog.domain.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.SystemSettings;
import org.personal.mason.feop.server.blog.domain.repository.SystemSettingsRepository;
import org.personal.mason.feop.server.blog.domain.service.SystemSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemSettingsServiceImpl implements SystemSettingsService {

	private SystemSettingsRepository systemSettingsRepository;

	@Autowired
	public void setSystemSettingsRepository(SystemSettingsRepository systemSettingsRepository) {
		this.systemSettingsRepository = systemSettingsRepository;
	}

	@Override
	public void save(SystemSettings... settings) {
		if (settings.length > 0) {
			systemSettingsRepository.save(Arrays.asList(settings));
		}
	}

	@Override
	public void delete(SystemSettings... settings) {
		if (settings.length > 0) {
			systemSettingsRepository.delete(Arrays.asList(settings));
		}
	}

	@Override
	public List<SystemSettings> findByProfile(String profile) {
		return systemSettingsRepository.findByProfile(profile);
	}

	@Override
	public List<SystemSettings> update(SystemSettings... settings) {
		if (settings.length > 0) {
			List<SystemSettings> ret = new ArrayList<>();
			for (SystemSettings systemSettings : settings) {
				SystemSettings updated = systemSettingsRepository.saveAndFlush(systemSettings);
				ret.add(updated);
			}
			return ret;
		}
		return Collections.emptyList();
	}

}
