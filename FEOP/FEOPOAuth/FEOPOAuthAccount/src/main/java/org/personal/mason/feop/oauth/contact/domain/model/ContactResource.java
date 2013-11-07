package org.personal.mason.feop.oauth.contact.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the contact_resource database table.
 * 
 */
@Entity
@Table(name = "contact_resource")
@NamedQuery(name = "ContactResource.findAll", query = "SELECT c FROM ContactResource c")
public class ContactResource extends FOEPPersistable<Long> {

private static final long serialVersionUID = -2726727210425809677L;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "build_date")
private Date buildDate;

@Lob
private String description;

@Column(name = "resource_name", length = 255)
private String resourceName;

// bi-directional many-to-one association to Contact
@ManyToOne
private Contact contact;

public ContactResource() {
}

public Date getBuildDate() {
	return this.buildDate;
}

public void setBuildDate(Date buildDate) {
	this.buildDate = buildDate;
}

public String getDescription() {
	return this.description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getResourceName() {
	return this.resourceName;
}

public void setResourceName(String resourceName) {
	this.resourceName = resourceName;
}

public Contact getContact() {
	return this.contact;
}

public void setContact(Contact contact) {
	this.contact = contact;
}

}