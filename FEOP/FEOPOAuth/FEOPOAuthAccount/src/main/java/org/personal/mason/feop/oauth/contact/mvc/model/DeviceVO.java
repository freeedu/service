package org.personal.mason.feop.oauth.contact.mvc.model;

import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("device")
public class DeviceVO {

private Long id;
private String name;
private String identifier;
private String machineAddress;
private String type;
private Long accountId;
private int version;
private String oauthUser;
private String oauthSecret;
private String phoneNumber;
private String oauthUid;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getIdentifier() {
	return identifier;
}

public void setIdentifier(String identifier) {
	this.identifier = identifier;
}

public String getMachineAddress() {
	return machineAddress;
}

public void setMachineAddress(String machineAddress) {
	this.machineAddress = machineAddress;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public Long getAccountId() {
	return accountId;
}

public void setAccountId(Long accountId) {
	this.accountId = accountId;
}

public int getVersion() {
	return version;
}

public void setVersion(int version) {
	this.version = version;
}

public String getOauthUser() {
	return oauthUser;
}

public void setOauthUser(String oauthUser) {
	this.oauthUser = oauthUser;
}

public String getOauthSecret() {
	return oauthSecret;
}

public void setOauthSecret(String oauthSecret) {
	this.oauthSecret = oauthSecret;
}

public String getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public String getOauthUid() {
	return oauthUid;
}

public void setOauthUid(String oauthUid) {
	this.oauthUid = oauthUid;
}

}
