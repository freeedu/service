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
 * The persistent class for the contact_email database table.
 * 
 */
@Entity
@Table(name = "contact_email")
@NamedQuery(name = "ContactEmail.findAll", query = "SELECT c FROM ContactEmail c")
public class ContactEmail extends AbstractPersistable<Long> {

private static final long serialVersionUID = 5355035111588385108L;

@Temporal(TemporalType.TIMESTAMP)
@Column(name="create_time", nullable=false)
private Date createTime;

@Column(name="email_address", nullable=false, length=255)
private String emailAddress;

@Column(name="email_label", length=40)
private String emailLabel;

@Temporal(TemporalType.TIMESTAMP)
@Column(name="last_update_time")
private Date lastUpdateTime;

@Column(nullable=false)
private int priority;

@Column(nullable=false)
private int version;

//bi-directional many-to-one association to Contact
@ManyToOne
@JoinColumn(name="contact_id", nullable=false)
private Contact contact;

public ContactEmail() {
}

public Date getCreateTime() {
	return this.createTime;
}

public void setCreateTime(Date createTime) {
	this.createTime = createTime;
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

public Date getLastUpdateTime() {
	return this.lastUpdateTime;
}

public void setLastUpdateTime(Date lastUpdateTime) {
	this.lastUpdateTime = lastUpdateTime;
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