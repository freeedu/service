package org.personal.mason.feop.oauth.account.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.jpa.domain.AbstractPersistable;

@XmlRootElement
@Entity
@Table(name = "user_resource", schema = "account")
public class UserResource extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -5091483402074546672L;
	@Column(name = "resource_name")
	private String resourceName;
	@Column(name = "build_date")
	private Date buildDate;
	@Lob
	@Column(name = "description")
	private String description;
	@ManyToOne
	@JoinColumn(name = "account_id", insertable = false, updatable = false)
	private AccountUser accountUser;

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Date getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AccountUser getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(AccountUser accountUser) {
		this.accountUser = accountUser;
	}

}