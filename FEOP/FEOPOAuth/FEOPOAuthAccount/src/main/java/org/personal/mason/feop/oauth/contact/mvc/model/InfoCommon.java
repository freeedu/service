package org.personal.mason.feop.oauth.contact.mvc.model;

import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("info")
public class InfoCommon {

private Long id;
private String label;
private String value;
private int priority;
private int version;
private Long contact;
private Long infoType;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

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

public int getVersion() {
	return version;
}

public void setVersion(int version) {
	this.version = version;
}

public Long getContact() {
	return contact;
}

public void setContact(Long contact) {
	this.contact = contact;
}

public Long getInfoType() {
	return infoType;
}

public void setInfoType(Long infoType) {
	this.infoType = infoType;
}

}
