package org.personal.mason.feop.oauth.contact.domain.model;

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

/**
 * The persistent class for the contact_record database table.
 * 
 */
@Entity
@Table(name = "contact_record")
@NamedQuery(name = "ContactRecord.findAll", query = "SELECT c FROM ContactRecord c")
public class ContactRecord extends FOEPPersistable<Long> {

private static final long serialVersionUID = -3829838155744166550L;

@Lob
private String accomplishment;

@Column(length = 255)
private String description;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "end_date")
private Date endDate;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "start_date")
private Date startDate;

@Column(length = 255)
private String type;

// bi-directional many-to-one association to Contact
@ManyToOne
@JoinColumn(name = "contact_id")
private Contact contact;

public ContactRecord() {
}

public String getAccomplishment() {
	return this.accomplishment;
}

public void setAccomplishment(String accomplishment) {
	this.accomplishment = accomplishment;
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

public Contact getContact() {
	return this.contact;
}

public void setContact(Contact contact) {
	this.contact = contact;
}

}