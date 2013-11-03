package org.personal.mason.feop.oauth.service.utils;

import java.util.Date;
import java.util.List;

import org.personal.mason.feop.oauth.service.domain.model.common.SystemSettings;

public class SystemSettingUtils {

	public static String getValue(List<SystemSettings> settings) {

		if (settings != null && settings.size() > 0) {
			SystemSettings setting = settings.get(0);
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
