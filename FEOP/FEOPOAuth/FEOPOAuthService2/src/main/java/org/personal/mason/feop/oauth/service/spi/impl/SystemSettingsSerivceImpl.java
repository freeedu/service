package org.personal.mason.feop.oauth.service.spi.impl;

import java.util.Arrays;
import java.util.List;

import org.personal.mason.feop.oauth.service.domain.SystemSettings;
import org.personal.mason.feop.oauth.service.repository.SystemSettingsRepository;
import org.personal.mason.feop.oauth.service.spi.SystemSettingsService;
import org.personal.mason.feop.oauth.service.utils.PageableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemSettingsSerivceImpl implements SystemSettingsService {

	private SystemSettingsRepository systemSettingsRepository;

	@Autowired
	public void setSystemSettingsRepository(SystemSettingsRepository systemSettingsRepository) {
		this.systemSettingsRepository = systemSettingsRepository;
	}

	@Override
	@Transactional
	public void save(SystemSettings settings) {
		systemSettingsRepository.save(settings);
	}

	@Override
	@Transactional
	public SystemSettings update(SystemSettings settings) {
		return systemSettingsRepository.saveAndFlush(settings);
	}

	@Override
	@Transactional
	public Page<SystemSettings> findAll(int page, int size) {
		return systemSettingsRepository.findAll(PageableUtils.getPageable(page, size));
	}

	@Override
	@Transactional
	public void delete(SystemSettings... settings) {
		systemSettingsRepository.delete(Arrays.asList(settings));
	}

	@Override
	public SystemSettings findById(Long id) {
		return systemSettingsRepository.findOne(id);
	}
	
	@Override
	public List<SystemSettings> findByKey(String key){
		return systemSettingsRepository.findByKey(key);
	}

}
