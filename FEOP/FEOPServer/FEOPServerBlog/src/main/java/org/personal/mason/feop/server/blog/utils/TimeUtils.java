package org.personal.mason.feop.server.blog.utils;

import java.sql.Timestamp;
import java.util.Calendar;

public class TimeUtils {

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(Calendar.getInstance().getTime().getTime());
	}

}
