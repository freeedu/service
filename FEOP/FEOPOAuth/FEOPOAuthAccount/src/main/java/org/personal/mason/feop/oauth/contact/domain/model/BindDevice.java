package org.personal.mason.feop.oauth.contact.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the bind_devices database table.
 * 
 */
@Entity
@Table(name = "bind_devices")
@NamedQuery(name = "BindDevice.findAll", query = "SELECT b FROM BindDevice b")
public class BindDevice extends FOEPPersistable<Long> {

private static final long serialVersionUID = -1532782506884453999L;

@Column(name = "device_identifier", nullable = false, length = 128)
private String deviceIdentifier;

@Column(name = "device_type", nullable = false, length = 64)
private String deviceType;

@Column(name = "device_name", length = 128)
private String deviceName;

@Column(name = "mac_addr", length = 128)
private String macAddress;

@Column(name = "phone_no", length = 64)
private String phoneNumber;

// bi-directional many-to-one association to AccountBasic
@ManyToOne
@JoinColumn(name = "account_id")
private AccountBasic accountBasic;

public BindDevice() {
}

public String getDeviceIdentifier() {
	return this.deviceIdentifier;
}

public void setDeviceIdentifier(String deviceIdentifier) {
	this.deviceIdentifier = deviceIdentifier;
}

public String getDeviceType() {
	return this.deviceType;
}

public void setDeviceType(String deviceType) {
	this.deviceType = deviceType;
}

public AccountBasic getAccountBasic() {
	return this.accountBasic;
}

public void setAccountBasic(AccountBasic accountBasic) {
	this.accountBasic = accountBasic;
}

public void setDeviceName(String deviceName) {
	this.deviceName = deviceName;
}

public String getDeviceName() {
	return deviceName;
}

public void setMacAddress(String macAddress) {
	this.macAddress = macAddress;
}

public String getMacAddress() {
	return macAddress;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public String getPhoneNumber() {
	return phoneNumber;
}

}