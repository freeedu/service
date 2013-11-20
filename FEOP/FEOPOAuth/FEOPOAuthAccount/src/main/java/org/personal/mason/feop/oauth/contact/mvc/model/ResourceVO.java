package org.personal.mason.feop.oauth.contact.mvc.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("resource")
public class ResourceVO {

private Long id;
private Date buildDay;
private String description;
private String resourceName;
private Long contact;
private int version;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Date getBuildDay() {
	return buildDay;
}

public void setBuildDay(Date buildDay) {
	this.buildDay = buildDay;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getResourceName() {
	return resourceName;
}

public void setResourceName(String resourceName) {
	this.resourceName = resourceName;
}

public Long getContact() {
	return contact;
}

public void setContact(Long contact) {
	this.contact = contact;
}

public int getVersion() {
	return version;
}

public void setVersion(int version) {
	this.version = version;
}

}
