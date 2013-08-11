package org.personal.mason.feop.oauth.service.utils;

import java.sql.Timestamp;
import java.util.Calendar;

public class TimeUtils {

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(Calendar.getInstance().getTime().getTime());
	}

}
