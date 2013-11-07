package org.personal.mason.feop.oauth.contact.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the contact_setting database table.
 * 
 */
@Entity
@Table(name = "contact_setting")
@NamedQuery(name = "ContactSetting.findAll", query = "SELECT c FROM ContactSetting c")
public class ContactSetting extends FOEPPersistable<Long> {

private static final long serialVersionUID = -1340773738671140162L;

@Column(name = "setting_key", nullable = false, length = 20)
private String settingKey;

@Column(name = "setting_value", nullable = false, length = 20)
private String settingValue;

// bi-directional many-to-one association to Contact
@ManyToOne
private Contact contact;

public ContactSetting() {
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

public Contact getContact() {
	return this.contact;
}

public void setContact(Contact contact) {
	this.contact = contact;
}

}