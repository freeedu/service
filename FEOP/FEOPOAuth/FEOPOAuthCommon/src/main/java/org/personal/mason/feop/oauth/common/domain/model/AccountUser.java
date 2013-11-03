package org.personal.mason.feop.oauth.common.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.personal.mason.feop.oauth.common.utils.DateUtils;
import org.springframework.data.jpa.domain.AbstractPersistable;

@XmlRootElement
@Entity
@Table(name = "account_info")
public class AccountUser extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 5893645164696913949L;

	@Column(name = "user_id", nullable = false, unique = true)
	private String userId;

	@Column(name = "user_name")
	private String username;
	@Column(name = "prefer_name")
	private String preferName;
	@Column(name = "birth")
	private Date birth;
	@Column(name = "birth_month_day")
	private String birthMonthDay;
	@Column(name = "nation")
	private String nation;
	@Lob
	@Column(name = "my_favor")
	private String favors;
	@Lob
	@Column(name = "my_goodat")
	private String goodat;
	@Lob
	@Column(name = "my_taboos")
	private String taboos;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountUser")
	private List<UserAddress> addresses = new ArrayList<UserAddress>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountUser")
	private List<UserPhone> phones = new ArrayList<UserPhone>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountUser")
	private List<UserEmail> emails = new ArrayList<UserEmail>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountUser")
	private List<UserIM> ims = new ArrayList<UserIM>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountUser")
	private List<UserRecord> records = new ArrayList<UserRecord>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountUser")
	private List<UserResource> resources = new ArrayList<UserResource>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPreferName() {
		return preferName;
	}

	public void setPreferName(String preferName) {
		this.preferName = preferName;
	}

	public Date getBirth() {
		if (this.birthMonthDay == null) {
			this.birthMonthDay = DateUtils.getMonthAndDay(birth);
		}
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getFavors() {
		return favors;
	}

	public void setFavors(String favors) {
		this.favors = favors;
	}

	public String getGoodat() {
		return goodat;
	}

	public void setGoodat(String goodat) {
		this.goodat = goodat;
	}

	public String getTaboos() {
		return taboos;
	}

	public void setTaboos(String taboos) {
		this.taboos = taboos;
	}

	public List<UserAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<UserAddress> addresses) {
		this.addresses = addresses;
	}

	public List<UserPhone> getPhones() {
		return phones;
	}

	public void setPhones(List<UserPhone> phones) {
		this.phones = phones;
	}

	public List<UserEmail> getEmails() {
		return emails;
	}

	public void setEmails(List<UserEmail> emails) {
		this.emails = emails;
	}

	public List<UserIM> getIms() {
		return ims;
	}

	public void setIms(List<UserIM> ims) {
		this.ims = ims;
	}

	public List<UserRecord> getRecords() {
		return records;
	}

	public void setRecords(List<UserRecord> records) {
		this.records = records;
	}

	public List<UserResource> getResources() {
		return resources;
	}

	public void setResources(List<UserResource> resources) {
		this.resources = resources;
	}

	public String getBirthMonthDay() {
		return birthMonthDay;
	}

	public void setBirthMonthDay(String birthMonthDay) {
		this.birthMonthDay = birthMonthDay;
	}
}