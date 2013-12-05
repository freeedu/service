package org.personal.mason.feop.oauth.common.spi.impl;

import org.personal.mason.feop.oauth.common.domain.model.SystemSetting;
import org.personal.mason.feop.oauth.common.domain.repository.SystemSettingRepository;
import org.personal.mason.feop.oauth.common.spi.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SystemSettingServiceImpl implements SystemSettingService {

    private SystemSettingRepository systemSettingRepository;

    @Autowired
    public void setSystemSettingRepository(SystemSettingRepository systemSettingRepository) {
        this.systemSettingRepository = systemSettingRepository;
    }

    @Override
    public void save(SystemSetting... settings) {
        if (settings.length > 0) {
            systemSettingRepository.save(Arrays.asList(settings));
        }
    }

    @Override
    public void delete(SystemSetting... settings) {
        if (settings.length > 0) {
            systemSettingRepository.delete(Arrays.asList(settings));
        }
    }

    @Override
    public Page<SystemSetting> findAll(int page, int size) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<SystemSetting> findByProfile(String profile) {
        return systemSettingRepository.findByProfile(profile);
    }

    @Override
    public List<SystemSetting> update(SystemSetting... settings) {
        if (settings.length > 0) {
            List<SystemSetting> ret = new ArrayList<>();
            for (SystemSetting systemSetting : settings) {
                SystemSetting updated = systemSettingRepository.saveAndFlush(systemSetting);
                ret.add(updated);
            }
            return ret;
        }
        return Collections.emptyList();
    }

    @Override
    public SystemSetting findById(Long id) {
        return systemSettingRepository.findOne(id);
    }

    @Override
    public List<SystemSetting> findByKey(String key) {
        return systemSettingRepository.findByKey(key);
    }

}
