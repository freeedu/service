package org.personal.mason.feop.oauth.contact.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the account_basic database table.
 * 
 */
@Entity
@Table(name = "account_basic")
@NamedQuery(name = "AccountBasic.findAll", query = "SELECT a FROM AccountBasic a")
public class AccountBasic extends FOEPPersistable<Long> {

private static final long serialVersionUID = 7792142130051760205L;

@Column(name = "account_uid", nullable = false, length = 64)
private String accountUid;

@Column(name = "oauth_uid", nullable = false, length = 64)
private String oauthUid;

// bi-directional many-to-one association to Contact
@ManyToOne
@JoinColumn(name = "contact_id", nullable = false)
private Contact contact;

// bi-directional many-to-one association to BindDevice
@OneToMany(mappedBy = "accountBasic")
private List<BindDevice> bindDevices;

public AccountBasic() {
}

public String getAccountUid() {
	return this.accountUid;
}

public void setAccountUid(String accountUid) {
	this.accountUid = accountUid;
}

public String getOauthUid() {
	return this.oauthUid;
}

public void setOauthUid(String oauthUid) {
	this.oauthUid = oauthUid;
}

public Contact getContact() {
	return this.contact;
}

public void setContact(Contact contact) {
	this.contact = contact;
}

public List<BindDevice> getBindDevices() {
	return this.bindDevices;
}

public void setBindDevices(List<BindDevice> bindDevices) {
	this.bindDevices = bindDevices;
}

public BindDevice addBindDevice(BindDevice bindDevice) {
	getBindDevices().add(bindDevice);
	bindDevice.setAccountBasic(this);
	
	return bindDevice;
}

public BindDevice removeBindDevice(BindDevice bindDevice) {
	getBindDevices().remove(bindDevice);
	bindDevice.setAccountBasic(null);
	
	return bindDevice;
}
}