package org.personal.mason.feop.oauth.contact.mvc.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("account")
public class Account {

private String id;
private String accountUid;
private String oauthUid;
private Contact contact;
private Device currentDevice;
private List<Device> devices = new ArrayList<>();
private int version;

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getAccountUid() {
	return accountUid;
}

public void setAccountUid(String accountUid) {
	this.accountUid = accountUid;
}

public String getOauthUid() {
	return oauthUid;
}

public void setOauthUid(String oauthUid) {
	this.oauthUid = oauthUid;
}

public Contact getContact() {
	return contact;
}

public void setContact(Contact contact) {
	this.contact = contact;
}

public Device getCurrentDevice() {
	return currentDevice;
}

public void setCurrentDevice(Device currentDevice) {
	this.currentDevice = currentDevice;
}

public List<Device> getDevices() {
	return devices;
}

public void setDevices(List<Device> devices) {
	this.devices = devices;
}

public int getVersion() {
	return version;
}

public void setVersion(int version) {
	this.version = version;
}

}
