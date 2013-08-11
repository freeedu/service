package org.personal.mason.feop.oauth.service.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "system_settings")
public class SystemSettings extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 268514826697363336L;

	@Column(name="setting_key")
	private String key;
	@Column(name="setting_value")
	private String value;
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "start_time")
	private Date startDate;
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "end_time", nullable = true)
	private Date endDate;
	private Boolean disabled;

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

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

}
