package org.personal.mason.feop.oauth.contact.domain.model.contacts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * The persistent class for the contact_resource database table.
 * 
 */
@Entity
@Table(name = "contact_resource")
@NamedQuery(name = "ContactResource.findAll", query = "SELECT c FROM ContactResource c")
public class ContactResource extends AbstractPersistable<Long> {

private static final long serialVersionUID = -2726727210425809677L;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "build_date")
private Date buildDate;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
private Date createTime;

@Lob
private String description;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "last_update_time")
private Date lastUpdateTime;

@Column(name="resource_name", length=255)
private String resourceName;

private int version;

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

public Date getLastUpdateTime() {
	return this.lastUpdateTime;
}

public void setLastUpdateTime(Date lastUpdateTime) {
	this.lastUpdateTime = lastUpdateTime;
}

public String getResourceName() {
	return this.resourceName;
}

public void setResourceName(String resourceName) {
	this.resourceName = resourceName;
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