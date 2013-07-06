package org.personal.mason.feop.oauth.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private final static DateFormat MONTH_AND_DAY = new SimpleDateFormat("MMdd");

	public static String getMonthAndDay(Date date) {
		return MONTH_AND_DAY.format(date);
	}
}
