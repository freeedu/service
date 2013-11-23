package org.personal.mason.feop.oauth.service.mvc.model;

import java.util.Date;

import org.personal.mason.feop.oauth.service.domain.model.common.SystemSettings;

public class SystemSettingForm {

	private Long id;
	private String key;
	private String value;
	private Date startDate;
	private Date endDate;
	private Boolean disabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean diabled) {
		this.disabled = diabled;
	}

	public static SystemSettingForm revert(SystemSettings setting) {
		SystemSettingForm form = new SystemSettingForm();
		form.setId(setting.getId());
		form.setKey(setting.getKey());
		form.setValue(setting.getValue());
		form.setStartDate(setting.getStartDate());
		form.setEndDate(setting.getEndDate());
		form.setDisabled(setting.getDisabled());
		return form;
	}

}
