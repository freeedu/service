package org.personal.mason.feop.oauth.contact.mvc.model;

import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("setting")
public class SettingVO {

private Long id;
private String key;
private String value;
private Long contact;
private int version;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getKey() {
	return key;
}

public void setKey(String key) {
	this.key = key;
}

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
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
