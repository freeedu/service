package org.personal.mason.feop.oauth.service.mvc.common;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateEditor extends PropertyEditorSupport {
	private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat TIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private DateFormat dateFormat;
	private boolean allowEmpty = true;

	public DateEditor() {
	}

	public DateEditor(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public DateEditor(DateFormat dateFormat, boolean allowEmpty) {
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			setValue(null);
		} else {

			try {
				if (this.dateFormat != null) {
					setValue(this.dateFormat.parse(text));
				} else {
					if (text.contains(":")) {
						setValue(TIMEFORMAT.parse(text));
					} else {
						DATEFORMAT.parse(text);
					}
				}
			} catch (ParseException e) {
				throw new IllegalArgumentException("Could not parse date:" + e.getMessage());
			}
		}
	}

	@Override
	public String getAsText() {
		Date date = (Date) getValue();
		DateFormat dateFormat = this.dateFormat;
		if (dateFormat == null) {
			dateFormat = TIMEFORMAT;
		}

		return date != null ? dateFormat.format(date) : "";
	}

}
