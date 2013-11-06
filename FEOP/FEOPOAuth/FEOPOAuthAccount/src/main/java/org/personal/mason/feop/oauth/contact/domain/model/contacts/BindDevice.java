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
 * The persistent class for the bind_devices database table.
 * 
 */
@Entity
@Table(name = "bind_devices")
@NamedQuery(name = "BindDevice.findAll", query = "SELECT b FROM BindDevice b")
public class BindDevice extends AbstractPersistable<Long> {

private static final long serialVersionUID = -1532782506884453999L;

@Temporal(TemporalType.TIMESTAMP)
@Column(name="create_time", nullable=false)
private Date createTime;

@Column(name="device_identifieer", nullable=false, length=128)
private String deviceIdentifieer;

@Column(name="device_type", nullable=false, length=64)
private String deviceType;

@Temporal(TemporalType.TIMESTAMP)
@Column(name="last_update_time")
private Date lastUpdateTime;

@Column(nullable=false)
private int version;

//bi-directional many-to-one association to AccountBasic
@ManyToOne
@JoinColumn(name="account_id")
private AccountBasic accountBasic;

public BindDevice() {
}

public Date getCreateTime() {
	return this.createTime;
}

public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}

public String getDeviceIdentifieer() {
	return this.deviceIdentifieer;
}

public void setDeviceIdentifieer(String deviceIdentifieer) {
	this.deviceIdentifieer = deviceIdentifieer;
}

public String getDeviceType() {
	return this.deviceType;
}

public void setDeviceType(String deviceType) {
	this.deviceType = deviceType;
}

public Date getLastUpdateTime() {
	return this.lastUpdateTime;
}

public void setLastUpdateTime(Date lastUpdateTime) {
	this.lastUpdateTime = lastUpdateTime;
}

public int getVersion() {
	return this.version;
}

public void setVersion(int version) {
	this.version = version;
}

public AccountBasic getAccountBasic() {
	return this.accountBasic;
}

public void setAccountBasic(AccountBasic accountBasic) {
	this.accountBasic = accountBasic;
}

}