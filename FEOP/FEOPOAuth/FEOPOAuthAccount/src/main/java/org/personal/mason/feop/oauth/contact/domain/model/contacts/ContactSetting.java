package org.personal.mason.feop.oauth.contact.domain.model.contacts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * The persistent class for the contact_setting database table.
 * 
 */
@Entity
@Table(name = "contact_setting")
@NamedQuery(name = "ContactSetting.findAll", query = "SELECT c FROM ContactSetting c")
public class ContactSetting extends AbstractPersistable<Long> {

private static final long serialVersionUID = -1340773738671140162L;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
private Date createTime;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "last_update_time")
private Date lastUpdateTime;

@Column(name = "setting_key", nullable = false, length = 20)
private String settingKey;

@Column(name = "setting_value", nullable = false, length = 20)
private String settingValue;

private int version;

// bi-directional many-to-one association to Contact
@ManyToOne
private Contact contact;

public ContactSetting() {
}

public Date getCreateTime() {
	return this.createTime;
}

public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}

public Date getLastUpdateTime() {
	return this.lastUpdateTime;
}

public void setLastUpdateTime(Date lastUpdateTime) {
	this.lastUpdateTime = lastUpdateTime;
}

public String getSettingKey() {
	return this.settingKey;
}

public void setSettingKey(String settingKey) {
	this.settingKey = settingKey;
}

public String getSettingValue() {
	return this.settingValue;
}

public void setSettingValue(String settingValue) {
	this.settingValue = settingValue;
}

public int getVersion() {
	return this.version;
}

public void setVersion(int version) {
	this.version = version;
}

public Contact getContact() {
	return this.contact;
}

public void setContact(Contact contact) {
	this.contact = contact;
}

}