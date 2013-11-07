package org.personal.mason.feop.oauth.contact.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the contact_email database table.
 * 
 */
@Entity
@Table(name = "contact_email")
@NamedQuery(name = "ContactEmail.findAll", query = "SELECT c FROM ContactEmail c")
public class ContactEmail extends FOEPPersistable<Long> {

private static final long serialVersionUID = 5355035111588385108L;

@Column(name = "email_address", nullable = false, length = 255)
private String emailAddress;

@Column(name = "email_label", length = 40)
private String emailLabel;

@Column(nullable = false)
private int priority;

// bi-directional many-to-one association to Contact
@ManyToOne
@JoinColumn(name = "contact_id", nullable = false)
private Contact contact;

public ContactEmail() {
}

public String getEmailAddress() {
	return this.emailAddress;
}

public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
}

public String getEmailLabel() {
	return this.emailLabel;
}

public void setEmailLabel(String emailLabel) {
	this.emailLabel = emailLabel;
}

public int getPriority() {
	return this.priority;
}

public void setPriority(int priority) {
	this.priority = priority;
}

public Contact getContact() {
	return this.contact;
}

public void setContact(Contact contact) {
	this.contact = contact;
}

}