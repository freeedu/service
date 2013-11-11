package org.personal.mason.feop.oauth.contact.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "contact_info_common")
@NamedQuery(name = "ContactInfoCommon.findAll", query = "select ic from ContactInfoCommon ic")
public class ContactInfoCommon extends FOEPPersistable<Long> {

private static final long serialVersionUID = 4991220091180513745L;

@Column(name = "label", length = 40)
private String label;

@Column(name = "value", nullable = false, length = 255)
private String value;

@Column(nullable = false)
private int priority;

// bi-directional many-to-one association to Contact
@ManyToOne
@JoinColumn(name = "contact_id", nullable = false)
private Contact contact;

@ManyToOne
@JoinColumn(name = "type_id", nullable = false)
private ContactInfoType infoType;

public String getLabel() {
	return label;
}

public void setLabel(String label) {
	this.label = label;
}

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}

public int getPriority() {
	return priority;
}

public void setPriority(int priority) {
	this.priority = priority;
}

public Contact getContact() {
	return contact;
}

public void setContact(Contact contact) {
	this.contact = contact;
}

public ContactInfoType getInfoType() {
	return infoType;
}

public void setInfoType(ContactInfoType infoType) {
	this.infoType = infoType;
}

}
