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
@Table(name = "user_record", schema = "account")
public class UserRecord extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1107596563882680513L;

	@ManyToOne
	@JoinColumn(name = "account_id", insertable = false, updatable = false)
	private AccountUser accountUser;

	@Column(name = "type")
	private String type;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
	@Lob
	@Column(name = "accomplishment")
	private String accomplishment;

	@Column(name = "description")
	private String description;

	public AccountUser getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(AccountUser accountUser) {
		this.accountUser = accountUser;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAccomplishment() {
		return accomplishment;
	}

	public void setAccomplishment(String accomplishment) {
		this.accomplishment = accomplishment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}