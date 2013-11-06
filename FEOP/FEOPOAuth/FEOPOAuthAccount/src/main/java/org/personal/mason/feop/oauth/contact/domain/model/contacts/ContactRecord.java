package org.personal.mason.feop.oauth.contact.domain.model.contacts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * The persistent class for the contact_record database table.
 * 
 */
@Entity
@Table(name = "contact_record")
@NamedQuery(name = "ContactRecord.findAll", query = "SELECT c FROM ContactRecord c")
public class ContactRecord extends AbstractPersistable<Long> {

private static final long serialVersionUID = -3829838155744166550L;

@Lob
private String accomplishment;

@Temporal(TemporalType.TIMESTAMP)
@Column(name="create_time", nullable=false)
private Date createTime;

@Column(length=255)
private String description;

@Temporal(TemporalType.TIMESTAMP)
@Column(name="end_date")
private Date endDate;

@Temporal(TemporalType.TIMESTAMP)
@Column(name="last_update_time")
private Date lastUpdateTime;

@Temporal(TemporalType.TIMESTAMP)
@Column(name="start_date")
private Date startDate;

@Column(length=255)
private String type;

@Column(nullable=false)
private int version;

//bi-directional many-to-one association to Contact
@ManyToOne
@JoinColumn(name="contact_id")
private Contact contact;

public ContactRecord() {
}

public String getAccomplishment() {
	return this.accomplishment;
}

public void setAccomplishment(String accomplishment) {
	this.accomplishment = accomplishment;
}

public Date getCreateTime() {
	return this.createTime;
}

public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}

public String getDescription() {
	return this.description;
}

public void setDescription(String description) {
	this.description = description;
}

public Date getEndDate() {
	return this.endDate;
}

public void setEndDate(Date endDate) {
	this.endDate = endDate;
}

public Date getLastUpdateTime() {
	return this.lastUpdateTime;
}

public void setLastUpdateTime(Date lastUpdateTime) {
	this.lastUpdateTime = lastUpdateTime;
}

public Date getStartDate() {
	return this.startDate;
}

public void setStartDate(Date startDate) {
	this.startDate = startDate;
}

public String getType() {
	return this.type;
}

public void setType(String type) {
	this.type = type;
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