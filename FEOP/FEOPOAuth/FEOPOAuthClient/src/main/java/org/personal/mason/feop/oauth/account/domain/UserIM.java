package org.personal.mason.feop.oauth.account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.jpa.domain.AbstractPersistable;

@XmlRootElement
@Entity
@Table(name = "user_im", schema = "account")
public class UserIM extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 3198628485147962004L;
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private AccountUser accountUser;
	@Column(name = "label")
	private String label;
	@Column(name = "content")
	private String content;
	@Column(name = "prefer")
	private boolean primary = false;

	public AccountUser getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(AccountUser accountUser) {
		this.accountUser = accountUser;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

}
