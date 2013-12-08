package org.personal.mason.feop.oauth.common.spi;

import org.personal.mason.feop.oauth.common.domain.model.SystemSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/7/13
 * Time: 2:53 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class SettingsHolder {

    private SystemSettingService systemSettingService;

    @Autowired
    public void setSystemSettingService(SystemSettingService systemSettingService) {
        this.systemSettingService = systemSettingService;
    }

    private ConcurrentHashMap<String, SystemSetting> settingContainer = new ConcurrentHashMap(50);

    public SystemSetting findWithKey(String key){
        if(settingContainer.containsKey(key)){
            return settingContainer.get(key);
        }

        return findAndReloadWithKey(key);
    }

    public SystemSetting findAndReloadWithKey(String key){
        List<SystemSetting> settings = systemSettingService.findByKey(key);
        if(!settings.isEmpty()){
            settingContainer.put(key, settings.get(0));
            return settingContainer.get(key);
        } else {
            return null;
        }
    }

    public void reload(){
        settingContainer.clear();
    }

}
