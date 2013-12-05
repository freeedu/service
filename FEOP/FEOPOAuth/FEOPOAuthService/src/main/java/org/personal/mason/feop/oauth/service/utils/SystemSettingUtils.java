package org.personal.mason.feop.oauth.service.utils;

import org.personal.mason.feop.oauth.common.domain.model.SystemSetting;

import java.util.Date;
import java.util.List;

public class SystemSettingUtils {

    public static String getValue(List<SystemSetting> settings) {

        if (settings != null && settings.size() > 0) {
            SystemSetting setting = settings.get(0);
            boolean valid = true;
            if (setting.getDisabled()) {
                valid = false;
            }
            Date now = TimeUtils.getCurrentTimestamp();
            if (setting.getStartDate().after(now)) {
                valid = false;
            }

            if (setting.getEndDate() != null && setting.getEndDate().before(now)) {
                valid = false;
            }

            if (valid) {
                return setting.getValue();
            }
        }
        return "";
    }
}
