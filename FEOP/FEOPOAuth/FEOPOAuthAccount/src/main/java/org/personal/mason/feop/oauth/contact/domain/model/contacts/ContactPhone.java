package org.personal.mason.feop.oauth.contact.domain.model.contacts;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the contact_phone database table.
 * 
 */
@Entity
@Table(name = "contact_phone")
@NamedQuery(name = "ContactPhone.findAll", query = "SELECT c FROM ContactPhone c")
public class ContactPhone implements Serializable {

private static final long serialVersionUID = -8693624053049777573L;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time", nullable = false)
private Date createTime;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "last_update_time")
private Date lastUpdateTime;

@Column(name = "phone_label", length = 255)
private String phoneLabel;

@Column(name = "phone_number", nullable = false, length = 40)
private String phoneNumber;

@Column(nullable = false)
private int priority;

@Column(nullable = false)
private int version;

// bi-directional many-to-one association to Contact
@ManyToOne
@JoinColumn(name = "contact_id", nullable = false)
private Contact contact;

public ContactPhone() {
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

public String getPhoneLabel() {
	return this.phoneLabel;
}

public void setPhoneLabel(String phoneLabel) {
	this.phoneLabel = phoneLabel;
}

public String getPhoneNumber() {
	return this.phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public int getPriority() {
	return this.priority;
}

public void setPriority(int priority) {
	this.priority = priority;
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