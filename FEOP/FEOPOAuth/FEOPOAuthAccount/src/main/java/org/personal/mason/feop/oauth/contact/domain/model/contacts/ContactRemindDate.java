package org.personal.mason.feop.oauth.contact.domain.model.contacts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * The persistent class for the contact_remind_date database table.
 * 
 */
@Entity
@Table(name = "contact_remind_date")
@NamedQuery(name = "ContactRemindDate.findAll", query = "SELECT c FROM ContactRemindDate c")
public class ContactRemindDate extends AbstractPersistable<Long> {

private static final long serialVersionUID = -7014293686255922337L;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time", nullable = false)
private Date createTime;

@Column(length = 60)
private String label;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "last_update_time")
private Date lastUpdateTime;

@Column(nullable = false)
private Boolean remind;

@Temporal(TemporalType.DATE)
@Column(name = "remind_date", nullable = false)
private Date remindDate;

@Column(nullable = false)
private int version;

// bi-directional many-to-one association to Contact
@ManyToOne
@JoinColumn(name = "contact_id", nullable = false)
private Contact contact;

public ContactRemindDate() {
}

public Date getCreateTime() {
	return this.createTime;
}

public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}

public String getLabel() {
	return this.label;
}

public void setLabel(String label) {
	this.label = label;
}

public Date getLastUpdateTime() {
	return this.lastUpdateTime;
}

public void setLastUpdateTime(Date lastUpdateTime) {
	this.lastUpdateTime = lastUpdateTime;
}

public Boolean getRemind() {
	return this.remind;
}

public void setRemind(Boolean remind) {
	this.remind = remind;
}

public Date getRemindDate() {
	return this.remindDate;
}

public void setRemindDate(Date remindDate) {
	this.remindDate = remindDate;
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