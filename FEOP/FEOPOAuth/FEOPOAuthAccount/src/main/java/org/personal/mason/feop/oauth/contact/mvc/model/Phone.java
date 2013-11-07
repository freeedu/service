package org.personal.mason.feop.oauth.contact.mvc.model;

import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("phone")
public class Phone {

private Long id;
private String label;
private String number;
private int priority;
private Long contact;
private int verison;

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

public String getNumber() {
	return number;
}

public void setNumber(String number) {
	this.number = number;
}

public int getPriority() {
	return priority;
}

public void setPriority(int priority) {
	this.priority = priority;
}

public Long getContact() {
	return contact;
}

public void setContact(Long contact) {
	this.contact = contact;
}

public int getVerison() {
	return verison;
}

public void setVerison(int verison) {
	this.verison = verison;
}

}
